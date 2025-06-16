package br.com.kiev.temperature.monitoring.api.controller;

import br.com.kiev.temperature.monitoring.api.model.reponse.SensorAlertOutput;
import br.com.kiev.temperature.monitoring.api.model.request.SensorAlertInput;
import br.com.kiev.temperature.monitoring.domain.service.SensorAlertService;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/sensors/{sensorId}/alert")
@RequiredArgsConstructor
public class SensorAlertController {

    private final SensorAlertService service;

    @GetMapping
    public SensorAlertOutput getAlertConfiguration(@PathVariable TSID sensorId) {
        return service.getAlertConfiguration(sensorId);
    }

    @PutMapping
    public SensorAlertOutput createOrUpdate(@PathVariable TSID sensorId, @RequestBody SensorAlertInput input) {
        return service.update(sensorId, input);
    }

    @DeleteMapping()
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable TSID sensorId) {
        service.delete(sensorId);
    }
}