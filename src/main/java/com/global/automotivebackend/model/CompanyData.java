package com.global.automotivebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;


@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyData {
    @PrimaryKey
    private String companyId;
    private String companyName;
    private String companyAddress;
}