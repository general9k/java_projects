package ru.rodionov.sensor.project3.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.rodionov.sensor.project3.dto.MeasureDTO;
import ru.rodionov.sensor.project3.models.Measure;
import ru.rodionov.sensor.project3.services.MeasureService;
import ru.rodionov.sensor.project3.util.ErrorMessage;
import ru.rodionov.sensor.project3.util.ErrorResponse;
import ru.rodionov.sensor.project3.util.MeasureValidator;
import ru.rodionov.sensor.project3.util.ModelCreationError;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasureController {

    private final MeasureService measureService;

    private final MeasureValidator measureValidator;

    private final ModelMapper modelMapper;

    @Autowired
    public MeasureController(MeasureService measureService, MeasureValidator measureValidator, ModelMapper modelMapper) {
        this.measureService = measureService;
        this.measureValidator = measureValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<MeasureDTO> getMeasures() {
        return measureService.findAll()
                .stream().map(this::convertToMeasureDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public List<MeasureDTO> getRainyDays() {
        return measureService.findAllRainingDays()
                .stream().map(this::convertToMeasureDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> createMeasure(@RequestBody @Valid MeasureDTO measureDTO,
                                                    BindingResult bindingResult) {
        Measure measure = convertToMeasure(measureDTO);

        measureValidator.validate(measure, bindingResult);

        if (bindingResult.hasErrors()) {
            ErrorMessage.creatingErrorMessage(bindingResult);
        }

        measureService.save(convertToMeasure(measureDTO));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerException(ModelCreationError e) {
        ErrorResponse response = new ErrorResponse(e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measure convertToMeasure(MeasureDTO dto) {
        return modelMapper.map(dto, Measure.class);
    }

    private MeasureDTO convertToMeasureDTO(Measure measure) {
        return modelMapper.map(measure, MeasureDTO.class);
    }

}
