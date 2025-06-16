package br.com.kiev.temperature.monitoring.api.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SensorAlertInput {
    private Double maxTemperature;
    private Double minTemperature;
}
