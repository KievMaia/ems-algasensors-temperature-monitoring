package br.com.kiev.temperature.monitoring.domain.service;

import br.com.kiev.temperature.monitoring.api.model.reponse.TemperatureLogData;
import br.com.kiev.temperature.monitoring.domain.mapper.TemperatureLogMapper;
import br.com.kiev.temperature.monitoring.domain.model.SensorId;
import br.com.kiev.temperature.monitoring.domain.repository.TemperatureLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TemperatureLogService {
    private final TemperatureLogRepository repository;

    public Page<TemperatureLogData> search(SensorId sensorId, Pageable pageable) {
        var temperatureLogs = repository.findAllBySensorId(sensorId, pageable);
        return temperatureLogs.map(TemperatureLogMapper::toTemperatureLogOutput);
    }
}
