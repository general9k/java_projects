package ru.rodionov.sensor.project3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurement")
public class Measure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "value")
    @NotNull(message = "Значение температуры не должно быть пустым")
    @DecimalMin(value = "-100.0", message = "Значение температуры должно быть больше -100 и менее 100")
    @DecimalMax(value = "100.0", message = "Значение температуры должно быть больше -100 и менее 100")
    private Double value;
    @Column(name = "raining")
    @NotNull(message = "Значение регистрации дождя не должно быть пустым")
    private Boolean raining;

    @Column(name = "measurement_date_time")
    @NotNull
    private LocalDateTime measurementDateTime;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean isRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getMeasurementDateTime() {
        return measurementDateTime;
    }

    public void setMeasurementDateTime(LocalDateTime measurementDateTime) {
        this.measurementDateTime = measurementDateTime;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
