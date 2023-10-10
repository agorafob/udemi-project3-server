package com.alik.project3.serverapi.dto;

import com.alik.project3.serverapi.models.Sensor;
import jakarta.validation.constraints.*;

public class MeasurementDTO {

    @NotNull(message = "Measurement value should not be empty")
    @DecimalMin(value = "-100.00", message = "Value should be min -100.00")
    @DecimalMax(value = "100.00", message = "Value should be max 100.00")
    private double value;

    @NotEmpty(message = "Raining report should not be empty")
    @Pattern(regexp = "^(true|false)$", message = "Raining report should be true or false")
    private String raining;


    @NotNull(message = "Sensor name should not be empty")
    private Sensor sensor;

    public MeasurementDTO() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getRaining() {
        return raining;
    }

    public void setRaining(String raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public boolean transferRaining(){
        return this.raining.equals("true");
    }


}
