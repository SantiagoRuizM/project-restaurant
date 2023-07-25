package com.dish.servicedish.exceptions;

public class WrongRoleException extends RuntimeException {

    public WrongRoleException(String message) {
        super(message);
    }
}
