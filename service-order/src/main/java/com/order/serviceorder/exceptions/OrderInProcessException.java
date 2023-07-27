package com.order.serviceorder.exceptions;

public class OrderInProcessException extends RuntimeException {

    public OrderInProcessException(String message) {
        super(message);
    }
}
