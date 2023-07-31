package com.order.serviceorder.exceptions;

public class OrderCancelledException extends RuntimeException {

    public OrderCancelledException(String message) {
        super(message);
    }
}
