package com.global.automotivebackend.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @PrimaryKey(value = "username")
    @NotNull(message = "Enter the username!!")
    @NotBlank(message = "Enter a valid username!!")
    private String username;


    @NotNull(message = "User email is mandatory!!")
    @NotBlank(message = "Enter a valid email id!")
    @Email(message = "Enter a valid email id")
    private String email;

    @Column(value = "password")
    @NotNull(message = "Password is necessary!!")
    @NotBlank(message = "Enter a valid password!!")
    private String password;

    @Column(value = "name")
    @NotNull(message = "Enter name of the user!!")
    @NotBlank(message = "Enter a valid name!!")
    private String name;
}