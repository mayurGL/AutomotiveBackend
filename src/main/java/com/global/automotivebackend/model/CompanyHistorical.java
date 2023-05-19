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
    @NotNull
    private int companyId;
    @Column(value = "company_name")
    @NotNull
    @NotBlank
    private String companyName;
    @Column(value = "company_address")
    @NotNull
    @NotBlank
    private String companyAddress;
    @Column(value = "created_time")
    private LocalDateTime createdTime;
    @Column(value = "modified_time")
    private LocalDateTime modifiedTime;
    @Column(value = "created_by")
    @NotNull
    @NotBlank
    private String createdBy;
    @Column(value = "modified_by")
    @NotNull
    @NotBlank
    private String modifiedBy;
}