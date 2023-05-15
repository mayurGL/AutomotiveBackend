package com.global.automotivebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;


@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {


    @PrimaryKey
    @Column(value = "company_id")
    private String company_id;

    private String created_time;

    private String modified_time;

    private String createdBy;

    private String modifiedBy;

    @Column(value = "company_name")
    private String companyName;

    @Column(value = "company_address")
    private String companyAddress;
}