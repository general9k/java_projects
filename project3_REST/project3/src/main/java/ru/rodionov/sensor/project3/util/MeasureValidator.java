package ru.rodionov.sensor.project3.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.rodionov.sensor.project3.models.Measure;
import ru.rodionov.sensor.project3.services.SensorService;

@Component
public class MeasureValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public MeasureValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measure.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measure measure = (Measure) target;
        if (sensorService.getSensorByName(measure.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "",
                    "Данный сенсор не зарегистрирован в системе");
        }
    }
}
