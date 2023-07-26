package com.order.serviceorder.exceptions;

public class DishFailedResponseController extends RuntimeException {

    public DishFailedResponseController(String message) {
        super(message);
    }
}
