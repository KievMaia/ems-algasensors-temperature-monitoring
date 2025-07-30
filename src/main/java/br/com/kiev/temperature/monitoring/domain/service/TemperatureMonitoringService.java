package br.com.kiev.temperature.monitoring.domain.service;

import br.com.kiev.temperature.monitoring.api.model.reponse.TemperatureLogData;
import br.com.kiev.temperature.monitoring.domain.model.SensorId;
import br.com.kiev.temperature.monitoring.domain.model.SensorMonitoring;
import br.com.kiev.temperature.monitoring.domain.model.TemperatureLog;
import br.com.kiev.temperature.monitoring.domain.model.TemperatureLogId;
import br.com.kiev.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import br.com.kiev.temperature.monitoring.domain.repository.TemperatureLogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static br.com.kiev.temperature.monitoring.domain.mapper.TemperatureLogMapper.toTemperatureLogEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemperatureMonitoringService {

    private final SensorMonitoringService sensorMonitoringService;
    private final TemperatureLogRepository temperatureLogRepository;

    @Transactional
    public void processTemperatureReading(TemperatureLogData temperatureLogData) {
        sensorMonitoringService.findById(temperatureLogData.getSensorId())
                .ifPresentOrElse(sensor -> this.handleSensorMonitoring(temperatureLogData, sensor),
                        () -> this.logIgnoreTemperature(temperatureLogData));
    }

    private void logIgnoreTemperature(TemperatureLogData temperatureLogData) {
        log.info("Temperature Ignored: SensorId {} Temp {}", temperatureLogData.getSensorId(), temperatureLogData.getValue());
    }

    private void handleSensorMonitoring(TemperatureLogData temperatureLogData, SensorMonitoring sensor) {
        if (sensor.isEnabled()) {
            sensor.setLastTemperature(temperatureLogData.getValue());
            sensorMonitoringService.save(sensor);

            temperatureLogRepository.save(toTemperatureLogEntity(temperatureLogData));
            log.info("Temperature Updated: SensorId {} Temp {}", temperatureLogData.getSensorId(), temperatureLogData.getValue());
        } else {
            logIgnoreTemperature(temperatureLogData);
        }
    }
}
