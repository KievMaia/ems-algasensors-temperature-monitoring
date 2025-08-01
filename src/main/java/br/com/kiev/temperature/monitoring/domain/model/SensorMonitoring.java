package br.com.kiev.temperature.monitoring.domain.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SensorMonitoring {
    @Id
    @AttributeOverride(name = "value", column = @Column(name = "id", columnDefinition = "bigint"))
    private SensorId id;
    private Double lastTemperature;
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
    private Boolean enabled;

    public void enabled() {
        this.enabled = true;
    }
    public void disabled() {
        this.enabled = false;
    }

    public boolean isEnabled() {
        return Boolean.TRUE.equals(enabled);
    }
}
