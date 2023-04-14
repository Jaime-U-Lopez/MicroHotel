package com.example.reservas.Dto;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.List;

public class ApiException {

    private  String error;
    private  String message;
    private  HttpStatus httpStatus;
    private ZonedDateTime timestamp;


    public ApiException(String message, HttpStatus httpStatus, ZonedDateTime timestamp) {
        this.error="Error: ";
        this.message = message;
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}




