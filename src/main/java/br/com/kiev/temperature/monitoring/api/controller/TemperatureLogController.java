package br.com.kiev.temperature.monitoring.api.controller;

import br.com.kiev.temperature.monitoring.api.model.reponse.TemperatureLogOutput;
import br.com.kiev.temperature.monitoring.domain.model.SensorId;
import br.com.kiev.temperature.monitoring.domain.service.TemperatureLogService;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sensors/{sensorId}/temperatures")
@RequiredArgsConstructor
public class TemperatureLogController {
    private final TemperatureLogService service;

    @GetMapping
    public Page<TemperatureLogOutput> search(@PathVariable TSID sensorId, @PageableDefault Pageable pageable) {
        return service.search(new SensorId(sensorId), pageable);
    }
}
