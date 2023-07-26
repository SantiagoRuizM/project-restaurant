package com.order.serviceorder.exceptions;

public class InactiveDishException extends RuntimeException {

    public InactiveDishException(String message) {
        super(message);
    }
}
