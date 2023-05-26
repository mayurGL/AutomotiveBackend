package com.global.automotivebackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginUserRequest {

    @NotNull(message = "Enter the username!!")
    @NotBlank(message = "Enter a valid username!!")
    private String username;

    @NotNull(message = "Password is necessary!!")
    @NotBlank(message = "Enter a valid password!!")
    private String password;
}
