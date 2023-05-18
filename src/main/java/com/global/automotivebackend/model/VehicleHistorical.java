package com.global.automotivebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleHistorical {

    @PrimaryKey
    private UUID id;
    @Column(value = "vehicle_id")
    private int vehicleId;
    @Column(value = "company_id")
    private int companyId;
    @Column(value = "make")
    private String make;
    @Column(value = "model")
    private String model;
    @Column(value = "year")
    private int year;
    @Column(value = "created_time")
    private LocalDateTime createdTime;
    @Column(value = "modified_time")
    private LocalDateTime modifiedTime;
    @Column(value = "created_by")
    private String createdBy;
    @Column(value = "modified_by")
    private String modifiedBy;
}