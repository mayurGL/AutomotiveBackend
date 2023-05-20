package com.global.automotivebackend.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Table
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gps {

    @PrimaryKeyColumn(name="created_time", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
    private LocalDateTime createdTime;

    @PrimaryKeyColumn(name = "vehicle_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private int vehicleId;

    @PrimaryKeyColumn(name = "device_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private int deviceId;

    @PrimaryKeyColumn(name = "company_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
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