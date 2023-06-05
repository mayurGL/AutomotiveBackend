package com.global.automotivebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Class to help in sending response in custom format
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {
    private String message;
    private boolean status;
}
