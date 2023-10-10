package com.alik.project3.serverapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "value")
    @DecimalMin(value = "-100.00",message = "Value should be min -100.00")
    @DecimalMax(value = "100.00",message = "Value should be max 100.00")
    private double value;

    @Column(name = "raining")
    @NotNull(message = "Raining report should not be empty")
    private boolean raining;

    @Column(name = "received_at")
    private LocalDateTime received_at;

    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;

    public Measurement() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getReceived_at() {
        return received_at;
    }

    public void setReceived_at(LocalDateTime received_at) {
        this.received_at = received_at;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

}
