package br.com.kiev.temperature.monitoring.domain.mapper;

import br.com.kiev.temperature.monitoring.api.model.reponse.TemperatureLogOutput;
import br.com.kiev.temperature.monitoring.domain.model.TemperatureLog;

public class TemperatureLogMapper {

    private TemperatureLogMapper() {}

    public static TemperatureLogOutput toTemperatureLogOutput(TemperatureLog temperatureLog) {
        return TemperatureLogOutput.builder()
                .id(temperatureLog.getId().getValue())
                .value(temperatureLog.getValue())
                .sensorId(temperatureLog.getSensorId().getValue())
                .registeredAt(temperatureLog.getRegisteredAt())
                .build();
    }
}
