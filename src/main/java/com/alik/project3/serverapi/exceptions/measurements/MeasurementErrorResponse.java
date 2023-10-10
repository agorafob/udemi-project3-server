package com.alik.project3.serverapi.exceptions.measurements;

import java.time.LocalDateTime;

public class MeasurementErrorResponse {
    private String message;
    private LocalDateTime createdAt;

    public MeasurementErrorResponse(String message, LocalDateTime createdAt) {
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
