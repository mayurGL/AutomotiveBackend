package com.global.automotivebackend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
 * Entity Class for vehicle table
 */
@Table
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @PrimaryKey(value = "vehicle_id")
    @NotNull(message = "Enter a valid vehicle ID!!")
    private Integer vehicleId;

    @Column(value = "company_id")
    @NotNull(message = "Enter a valid company ID!!")
    private Integer companyId;

    @Column(value = "make")
    @NotNull(message = "Enter the make of the vehicle!!")
    @NotBlank(message = "Enter the make of the vehicle!!")
    private String make;

    @Column(value = "model")
    @NotNull(message = "Enter the model of the vehicle!!")
    @NotBlank(message = "Enter the model of the vehicle!!")
    private String model;

    @Column(value = "year")
    @NotNull(message = "Enter a year of manufacturing!!")
    private Integer year;

    @Column(value = "created_time")
    private LocalDateTime createdTime;

    @Column(value = "modified_time")
    private LocalDateTime modifiedTime;

    @Column(value = "created_by")
    @NotNull(message = "Enter username!!")
    @NotBlank(message = "Enter username!!")
    private String createdBy;

    @Column(value = "modified_by")
    private String modifiedBy;
}