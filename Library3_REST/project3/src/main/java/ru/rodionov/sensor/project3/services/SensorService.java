package ru.rodionov.sensor.project3.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rodionov.sensor.project3.models.Sensor;
import ru.rodionov.sensor.project3.repositories.SensorRepository;
import ru.rodionov.sensor.project3.util.SensorNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Sensor findOne(int id) {
        return sensorRepository.findById(id).orElseThrow(SensorNotFoundException::new);
    }

    @Transactional
    public void register(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> getSensorByName(String name) {
        return sensorRepository.findByName(name);
    }

}
