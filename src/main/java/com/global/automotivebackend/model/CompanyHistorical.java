package com.global.automotivebackend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class CompanyHistorical {
    @PrimaryKey
    private UUID id;

    @Column(value = "company_id")
    @NotNull(message = "Enter a valid company ID!!")
    private Integer companyId;

    @Column(value = "company_name")
    @NotNull(message = "Enter a company name!!")
    @NotBlank(message = "Enter a valid company name!!")
    private String companyName;

    @Column(value = "company_address")
    @NotNull(message = "Enter the company address!!")
    @NotBlank(message = "Enter valid company address!!")
    private String companyAddress;

    @Column(value = "created_time")
    private LocalDateTime createdTime;

    @Column(value = "modified_time")
    private LocalDateTime modifiedTime;

    @Column(value = "created_by")
    @NotNull(message = "Enter username!!")
    @NotBlank(message = "Enter username!!")
    private String createdBy;

    @Column(value = "modified_by")
    @NotNull(message = "Enter username!!")
    @NotBlank(message = "Enter username!!")
    private String modifiedBy;
}