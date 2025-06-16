package br.com.kiev.temperature.monitoring.domain.repository;

import br.com.kiev.temperature.monitoring.domain.model.SensorAlert;
import br.com.kiev.temperature.monitoring.domain.model.SensorId;
import br.com.kiev.temperature.monitoring.domain.model.SensorMonitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorAlertRepository extends JpaRepository<SensorAlert, SensorId> {
}
