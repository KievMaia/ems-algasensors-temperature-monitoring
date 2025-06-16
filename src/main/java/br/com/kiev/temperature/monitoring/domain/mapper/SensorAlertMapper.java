package br.com.kiev.temperature.monitoring.domain.mapper;

import br.com.kiev.temperature.monitoring.api.model.reponse.SensorAlertOutput;
import br.com.kiev.temperature.monitoring.api.model.request.SensorAlertInput;
import br.com.kiev.temperature.monitoring.domain.model.SensorAlert;
import br.com.kiev.temperature.monitoring.domain.model.SensorId;

public class SensorAlertMapper {

    private SensorAlertMapper() {}

    public static SensorAlertOutput toSensorAlertOutput(SensorAlert sensorAlert) {
        return SensorAlertOutput.builder()
                .id(sensorAlert.getId().getValue())
                .maxTemperature(sensorAlert.getMaxTemperature())
                .minTemperature(sensorAlert.getMinTemperature())
                .build();
    }

    public static SensorAlert toSensorAlert(SensorAlertInput sensorAlertInput, SensorId sensorId) {
        return SensorAlert.builder()
                .id(sensorId)
                .maxTemperature(sensorAlertInput.getMaxTemperature())
                .minTemperature(sensorAlertInput.getMinTemperature())
                .build();
    }
}
