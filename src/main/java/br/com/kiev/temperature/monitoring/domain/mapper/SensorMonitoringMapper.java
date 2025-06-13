package br.com.kiev.temperature.monitoring.domain.mapper;

import br.com.kiev.temperature.monitoring.api.model.reponse.SensorMonitoringOutput;
import br.com.kiev.temperature.monitoring.domain.model.SensorMonitoring;

public class SensorMonitoringMapper {

    private SensorMonitoringMapper() {}

    public static SensorMonitoringOutput toSensorMonitoringOutput(SensorMonitoring sensorMonitoring) {
        return SensorMonitoringOutput.builder()
                .id(sensorMonitoring.getId().getValue())
                .enable(sensorMonitoring.getEnable())
                .lastTemperature(sensorMonitoring.getLastTemperature())
                .updatedAt(sensorMonitoring.getUpdatedAt())
                .build();
    }
}
