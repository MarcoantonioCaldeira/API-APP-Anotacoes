package com.apinote.service.exceptions;

public class EmailAlreadyExistisExceptions extends RuntimeException{

    public EmailAlreadyExistisExceptions(String message) {
        super(message);
    }

    public EmailAlreadyExistisExceptions(String message, Throwable cause) {
        super(message, cause);
    }
}
