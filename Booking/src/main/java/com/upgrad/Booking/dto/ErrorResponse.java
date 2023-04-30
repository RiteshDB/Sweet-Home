package com.upgrad.Booking.dto;

import java.util.List;

public class ErrorResponse {

    private String message;
    private int statusCode;

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "ErrorResponse{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
