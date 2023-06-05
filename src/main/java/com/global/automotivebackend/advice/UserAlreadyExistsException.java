package com.global.automotivebackend.advice;

/*
 * Custom Exception class for user already existing.
 */
public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}