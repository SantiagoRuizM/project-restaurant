package com.dish.servicedish.exceptions;

public class PriceNegativeException extends RuntimeException {

    public PriceNegativeException(String message) {
        super(message);
    }
}
