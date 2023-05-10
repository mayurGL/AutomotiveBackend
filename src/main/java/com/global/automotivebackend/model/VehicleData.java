package com.global.automotivebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

@Table
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleData {

    @PrimaryKey
    private String vehicleId;
    private String companyId;
    private String make;
    private String model;
    private int year;
}
