package br.com.kiev.temperature.monitoring.domain.service;

import br.com.kiev.temperature.monitoring.api.model.reponse.SensorMonitoringOutput;
import br.com.kiev.temperature.monitoring.domain.model.SensorId;
import br.com.kiev.temperature.monitoring.domain.model.SensorMonitoring;
import br.com.kiev.temperature.monitoring.domain.repository.SensorMonitoringRepository;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

import static br.com.kiev.temperature.monitoring.domain.mapper.SensorMonitoringMapper.toSensorMonitoringOutput;

@Service
@RequiredArgsConstructor
public class SensorMonitoringService {

    private final SensorMonitoringRepository repository;

    public void save(SensorMonitoring sensorMonitoring) {
        repository.save(sensorMonitoring);
    }

    public void enable(TSID sensorId) {
        var sensorMonitoring = this.findByIdOrDefault(sensorId);
        sensorMonitoring.enabled();
        this.save(sensorMonitoring);
    }

    @SneakyThrows
    public void disable(TSID sensorId) {
        var sensorMonitoring = this.findByIdOrDefault(sensorId);
        if(!sensorMonitoring.isEnabled()) {
            Thread.sleep(Duration.ofSeconds(10));
        }
        sensorMonitoring.disabled();
        this.save(sensorMonitoring);
    }

    public SensorMonitoringOutput getDetail(TSID sensorId) {
        var sensorMonitoring = this.findByIdOrDefault(sensorId);
        return toSensorMonitoringOutput(sensorMonitoring);
    }

    public SensorMonitoring findByIdOrDefault(TSID sensorId) {
        return repository.findById(new SensorId(sensorId)).orElse(SensorMonitoring.builder()
                .id(new SensorId(sensorId))
                .enabled(false)
                .lastTemperature(null)
                .updatedAt(null)
                .build());
    }

    public Optional<SensorMonitoring> findById(TSID sensorId) {
        return repository.findById(new SensorId(sensorId));
    }
}
