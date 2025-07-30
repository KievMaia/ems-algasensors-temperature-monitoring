package br.com.kiev.temperature.monitoring.domain.service;

import br.com.kiev.temperature.monitoring.api.model.reponse.SensorAlertOutput;
import br.com.kiev.temperature.monitoring.api.model.reponse.TemperatureLogData;
import br.com.kiev.temperature.monitoring.api.model.request.SensorAlertInput;
import br.com.kiev.temperature.monitoring.domain.model.SensorAlert;
import br.com.kiev.temperature.monitoring.domain.model.SensorId;
import br.com.kiev.temperature.monitoring.domain.repository.SensorAlertRepository;
import io.hypersistence.tsid.TSID;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

import static br.com.kiev.temperature.monitoring.domain.mapper.SensorAlertMapper.toSensorAlert;
import static br.com.kiev.temperature.monitoring.domain.mapper.SensorAlertMapper.toSensorAlertOutput;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class SensorAlertService {
    private final SensorAlertRepository repository;

    public SensorAlertOutput getAlertConfiguration(TSID tsid) {
        var sensorAlert = this.getOne(tsid);
        return toSensorAlertOutput(sensorAlert);
    }

    public SensorAlertOutput update(TSID tsid, SensorAlertInput input) {
        var sensorId = new SensorId(tsid);
        var sensorAlert = repository.findById(sensorId)
                .orElse(toSensorAlert(input, sensorId));
        BeanUtils.copyProperties(input, sensorAlert, "id");
        var sensorAlertUpdated = repository.save(sensorAlert);
        return toSensorAlertOutput(sensorAlertUpdated);
    }

    private SensorAlert getOne(TSID tsid) {
        return repository.findById(
                new SensorId(tsid)).orElseThrow(
                () -> new ResponseStatusException(NOT_FOUND, "Sensor alert configuration not found"));
    }

    public void delete(TSID tsid) {
        var sensorAlert = this.getOne(tsid);
        repository.delete(sensorAlert);
    }

    @Transactional
    public void handleAlert(TemperatureLogData temperatureLogData) {
        repository.findById(new SensorId(temperatureLogData.getSensorId()))
                .ifPresentOrElse(alert -> {
                    if (Objects.nonNull(alert.getMaxTemperature())
                            && temperatureLogData.getValue().compareTo(alert.getMaxTemperature()) >= 0) {
                        this.buildLogAlert("Alert Max Temp: SensorId {} Temp {}", temperatureLogData);
                    } else if (Objects.nonNull(alert.getMinTemperature())
                            && temperatureLogData.getValue().compareTo(alert.getMinTemperature()) <= 0) {
                        this.buildLogAlert("Alert Min Temp: SensorId {} Temp {}", temperatureLogData);
                    } else {
                        this.buildLogAlert("Alert Ignore Temp: SensorId {} Temp {}", temperatureLogData);
                    }
                }, () -> {
                    this.buildLogAlert("Alert Ignore: SensorId {} Temp {}", temperatureLogData);
                });
    }

    private void buildLogAlert(String message, TemperatureLogData temperatureLogData) {
        log.info(message, temperatureLogData.getSensorId(), temperatureLogData.getValue());
    }
}
