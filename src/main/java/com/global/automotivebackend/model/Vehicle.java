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
public class Vehicle {

    @PrimaryKey
    private String vehicle_id;
    @Column(value = "company_id")
    private String companyId;
    private String make;
    private String model;
    private int year;
    private String created_time;
    private String modified_time;
    private String createdBy;
    private String modifiedBy;
}