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

@Table
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @PrimaryKey(value = "vehicle_id")
    private int vehicleId;
    @Column(value = "company_id")
    @NotNull
    private int companyId;
    @NotNull
    @NotBlank
    private String make;
    @NotNull
    @NotBlank
    private String model;
    @NotNull
    private int year;
    @Column(value = "created_time")
    private LocalDateTime createdTime;
    @Column(value = "modified_time")
    private LocalDateTime modifiedTime;
    @Column(value = "created_by")
    @NotNull
    @NotBlank
    private String createdBy;
    @Column(value = "modified_by")
    @NotNull
    @NotBlank
    private String modifiedBy;
}