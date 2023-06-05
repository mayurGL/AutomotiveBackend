package com.global.automotivebackend.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * Class to help in displaying formatted validation failed messages
 */
@Data
@AllArgsConstructor
public class ApiError {

    String message;
    List<String> details;
    HttpStatus status;
    LocalDateTime timestamp;
}