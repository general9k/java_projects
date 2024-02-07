package ru.rodionov.sensor.project3.dto;

import jakarta.validation.constraints.*;

public class MeasureDTO {

    @NotNull(message = "Значение температуры не должно быть пустым")
    @DecimalMin(value = "-100.0", message = "Значение температуры должно быть больше -100 и менее 100")
    @DecimalMax(value = "100.0", message = "Значение температуры должно быть больше -100 и менее 100")
    private Double value;
    @NotNull(message = "Значение регистрации дождя не должно быть пустым")
    private Boolean raining;
    @NotNull(message = "Имя сенсора не должно быть пустым")
    private SensorDTO sensor;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }
}
