package com.global.automotivebackend.model;

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
public class Gps {

    @PrimaryKey(value = "created_time")
    @NotNull
    private LocalDateTime createdTime;

    @Column(value = "vehicle_id")
    @NotNull
    private int vehicleId;

    @Column(value = "device_id")
    @NotNull
    private int deviceId;

    @Column(value = "company_id")
    @NotNull
    private int companyId;

    @Column(value = "latitude")
    @NotNull
    private double latitude;

    @Column(value = "longitude")
    @NotNull
    private double longitude;

    @Column(value = "speed")
    @NotNull
    private double speed;
}