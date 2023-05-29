package com.global.automotivebackend.advice;

import java.time.LocalDateTime;
import java.util.*;

import com.global.automotivebackend.dto.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException error) {
        Map<String, String> errorMap = new HashMap<String, String>();
        error.getBindingResult().getFieldErrors().forEach(err -> {
            errorMap.put(err.getField(), err.getDefaultMessage());
        });
        return errorMap;
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> handleRegistrationFailedException(UserAlreadyExistsException ex) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Try Again Later");
        ApiError errors = new ApiError(message, details, HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<Object> handleAuthenticationFailedException(AuthenticationFailedException ex) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Please Use Valid Credentials!!");
        ApiError errors = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(IdNotFoundException ex) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Id is not valid!!");
        ApiError errors = new ApiError(message, details, HttpStatus.NOT_FOUND, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    @ExceptionHandler(IdAlreadyExistsException.class)
    public ResponseEntity<Object> handleBookTagException(IdAlreadyExistsException ex) {
        String message = ex.getMessage();
        List<String> details = new ArrayList<>();
        details.add("Id Already Exists!!");
        ApiError errors = new ApiError(message, details, HttpStatus.NOT_ACCEPTABLE, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errors);
    }
}