package com.global.automotivebackend.advice;

/*
 * Custom Exception class for failing authentication
 */
public class AuthenticationFailedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthenticationFailedException(String message) {
        super(message);
    }
}