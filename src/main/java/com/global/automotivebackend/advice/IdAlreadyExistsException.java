package com.global.automotivebackend.advice;

/*
 * Custom Exception class for ID already existing.
 */
public class IdAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IdAlreadyExistsException(String message) {
        super(message);
    }
}