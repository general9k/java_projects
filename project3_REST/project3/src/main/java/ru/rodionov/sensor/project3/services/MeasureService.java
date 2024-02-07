package ru.rodionov.sensor.project3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rodionov.sensor.project3.models.Measure;
import ru.rodionov.sensor.project3.repositories.MeasureRepository;
import ru.rodionov.sensor.project3.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasureService {
    private final MeasureRepository measureRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasureService(MeasureRepository measureRepository, SensorService sensorService) {
        this.measureRepository = measureRepository;
        this.sensorService = sensorService;
    }

    public List<Measure> findAll() {
        return measureRepository.findAll();
    }

    public List<Measure> findAllRainingDays() {
        return measureRepository.findByRaining(true);
    }

    public Measure findOne(int id) {
        Optional<Measure> measure = measureRepository.findById(id);
        return measure.orElse(null);
    }

    @Transactional
    public void save(Measure measure) {
        enrichMeasure(measure);

        measureRepository.save(measure);
    }

    private void enrichMeasure(Measure measure) {
        measure.setSensor(sensorService.getSensorByName(measure.getSensor().getName()).get());

        measure.setMeasurementDateTime(LocalDateTime.now());
    }

}
