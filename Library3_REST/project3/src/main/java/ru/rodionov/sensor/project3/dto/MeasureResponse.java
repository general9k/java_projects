package ru.rodionov.sensor.project3.dto;

import java.util.List;

public class MeasureResponse {
    private List<MeasureDTO> measure;

    public MeasureResponse(List<MeasureDTO> measure) {
        this.measure = measure;
    }

    public List<MeasureDTO> getMeasure() {
        return measure;
    }

    public void setMeasure(List<MeasureDTO> measure) {
        this.measure = measure;
    }
}
