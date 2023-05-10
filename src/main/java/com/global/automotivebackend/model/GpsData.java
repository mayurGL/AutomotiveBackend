package com.global.automotivebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Table
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GpsData {

    @PrimaryKey
    private UUID id;

    private String vehicleId;
    private String companyId;
    private double latitude;
    private double longitude;
    private double speed;
}
