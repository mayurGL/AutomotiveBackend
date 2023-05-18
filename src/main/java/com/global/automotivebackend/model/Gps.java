package com.global.automotivebackend.model;

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
    private LocalDateTime createdTime;

    @Column(value = "vehicle_id")
    private int vehicleId;

    @Column(value = "device_id")
    private int deviceId;

    @Column(value = "company_id")
    private int companyId;

    @Column(value = "latitude")
    private double latitude;

    @Column(value = "longitude")
    private double longitude;

    @Column(value = "speed")
    private double speed;
}
