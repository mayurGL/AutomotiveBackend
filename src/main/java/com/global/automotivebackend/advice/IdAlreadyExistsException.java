package com.global.automotivebackend.advice;

public class IdAlreadyExistsException extends RuntimeException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public IdAlreadyExistsException(String message) {
        super(message);
    }
}