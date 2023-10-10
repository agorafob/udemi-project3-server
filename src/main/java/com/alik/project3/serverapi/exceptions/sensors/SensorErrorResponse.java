package com.alik.project3.serverapi.exceptions.sensors;

import java.time.LocalDateTime;

public class SensorErrorResponse {
    private String message;
    private LocalDateTime createdAt;

    public SensorErrorResponse(String message, LocalDateTime createdAt) {
        this.message = message;
        this.createdAt = createdAt;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
