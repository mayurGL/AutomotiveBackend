package com.global.automotivebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

@Table
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gps {

    @PrimaryKey
    private String timeStatus;

    @Column(value = "vehicle_id")
    private String vehicleId;

    @Column(value = "device_id")
    private String deviceId;

    @Column(value = "company_id")
    private String companyId;

    @Column(value = "latitude")
    private double latitude;

    @Column(value = "longitude")
    private double longitude;

    @Column(value = "speed")
    private double speed;
}
