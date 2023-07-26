package com.order.serviceorder.dtos;

import org.springframework.http.HttpStatus;

public class ResponseErrorDto {

    private String message;
    private HttpStatus status;

    public ResponseErrorDto() {
    }

    public ResponseErrorDto(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
