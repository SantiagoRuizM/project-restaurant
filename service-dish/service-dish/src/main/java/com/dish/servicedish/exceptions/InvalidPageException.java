package com.dish.servicedish.exceptions;

public class InvalidPageException extends RuntimeException {

    public InvalidPageException(String message) {
        super(message);
    }
}
