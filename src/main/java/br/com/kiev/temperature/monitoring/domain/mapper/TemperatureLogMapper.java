package br.com.kiev.temperature.monitoring.domain.mapper;

import br.com.kiev.temperature.monitoring.api.model.reponse.TemperatureLogData;
import br.com.kiev.temperature.monitoring.domain.model.SensorId;
import br.com.kiev.temperature.monitoring.domain.model.TemperatureLog;
import br.com.kiev.temperature.monitoring.domain.model.TemperatureLogId;

public class TemperatureLogMapper {

    private TemperatureLogMapper() {}

    public static TemperatureLogData toTemperatureLogOutput(TemperatureLog temperatureLog) {
        return TemperatureLogData.builder()
                .id(temperatureLog.getId().getValue())
                .value(temperatureLog.getValue())
                .sensorId(temperatureLog.getSensorId().getValue())
                .registeredAt(temperatureLog.getRegisteredAt())
                .build();
    }

    public static TemperatureLog toTemperatureLogEntity(TemperatureLogData temperatureLogData) {
        return TemperatureLog.builder()
                .id(new TemperatureLogId(temperatureLogData.getId()))
                .registeredAt(temperatureLogData.getRegisteredAt())
                .value(temperatureLogData.getValue())
                .sensorId(new SensorId(temperatureLogData.getSensorId()))
                .build();
    }
}
