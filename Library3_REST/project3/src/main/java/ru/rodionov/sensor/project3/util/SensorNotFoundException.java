package ru.rodionov.sensor.project3.util;

public class SensorNotFoundException extends RuntimeException {
    public SensorNotFoundException() {
        super("Сенсор не найден в системе");
    }
}
