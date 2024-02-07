package ru.rodionov.sensor.project3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rodionov.sensor.project3.models.Measure;

import java.util.List;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Integer> {
    List<Measure> findByRaining(boolean raining);
}
