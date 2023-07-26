package com.order.serviceorder.dtos;

import org.springframework.http.HttpStatus;

public class ResponseErrorServerDto {

    private String message;
    private String messageServer;
    private HttpStatus status;

    public ResponseErrorServerDto() {
    }

    public ResponseErrorServerDto(String message, String messageServer, HttpStatus status) {
        this.message = message;
        this.messageServer = messageServer;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageServer() {
        return messageServer;
    }

    public void setMessageServer(String messageServer) {
        this.messageServer = messageServer;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
