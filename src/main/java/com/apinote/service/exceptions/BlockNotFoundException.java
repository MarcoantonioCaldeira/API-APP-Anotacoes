package com.apinote.service.exceptions;

public class BlockNotFoundException extends RuntimeException{

    public BlockNotFoundException(String message) {
        super(message);
    }

    public BlockNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
