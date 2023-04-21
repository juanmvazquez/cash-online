package com.challengecashonline.cashonline.model.exception;

public class DuplicatedUserException extends RuntimeException{
    public DuplicatedUserException(String message) {
        super(message);
    }
}
