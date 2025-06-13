package br.com.kiev.temperature.monitoring.api.controller;

import br.com.kiev.temperature.monitoring.api.model.reponse.SensorMonitoringOutput;
import br.com.kiev.temperature.monitoring.domain.service.SensorMonitoringService;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/sensors/{sensorId}/monitoring")
@RequiredArgsConstructor
public class SensorMonitoringController {

    private final SensorMonitoringService service;

    @GetMapping
    public SensorMonitoringOutput getDetail(@PathVariable TSID sensorId) {
        return service.getDetail(sensorId);
    }

    @PutMapping("/enable")
    @ResponseStatus(NO_CONTENT)
    public void enable(@PathVariable TSID sensorId) {
        service.enable(sensorId);
    }

    @DeleteMapping("/enable")
    @ResponseStatus(NO_CONTENT)
    public void disable(@PathVariable TSID sensorId) {
        service.disable(sensorId);
    }
}
