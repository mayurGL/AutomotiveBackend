package com.global.automotivebackend.advice;

/*
 * Custom Exception class for ID not found.
 */
public class IdNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public IdNotFoundException(String message) {
        super(message);
    }
}