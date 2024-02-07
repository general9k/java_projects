package ru.rodionov.sensor.project3.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.rodionov.sensor.project3.dto.SensorDTO;
import ru.rodionov.sensor.project3.models.Sensor;
import ru.rodionov.sensor.project3.services.SensorService;
import ru.rodionov.sensor.project3.util.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    // Вывести все сенсоры
    @GetMapping()
    public List<SensorDTO> getSensors() {
        return sensorService.findAll().stream().map(this::converToSensorDTO).collect(Collectors.toList());
        // Нашли все сенсоры, замапили в sensorDTO и сформировали коллекцию
    }

    // Вывести один сенсор по id, обработать ошибку SensorNotFoundException
    @GetMapping("/{id}")
    public SensorDTO getSensor(@PathVariable("id") int id) {
        return converToSensorDTO(sensorService.findOne(id));
    }

    // Обработка исключения "не найден сенсор"
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(SensorNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    // Create Sensor. Exception - not Valid field.
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {
        Sensor sensorToAdd = converToSensor(sensorDTO);

        sensorValidator.validate(sensorToAdd, bindingResult);

        if (bindingResult.hasErrors()) {
            // класс для создания сообщений ошибок и выбрасывания исключения ModelCreationError
            ErrorMessage.creatingErrorMessage(bindingResult);
        }
        sensorService.register(sensorToAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    // Обработка исключения создания человека через post запрос
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(ModelCreationError e) { // Обработка ModelCreationError
        ErrorResponse response = new ErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Sensor converToSensor(SensorDTO dto) {
        return modelMapper.map(dto, Sensor.class);
    }

    private SensorDTO converToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }

}
