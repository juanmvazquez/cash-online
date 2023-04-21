package com.challengecashonline.cashonline.model.exception;

public class LoanNotFoundException extends RuntimeException{
    public LoanNotFoundException(String message) {
        super(message);
    }
}
