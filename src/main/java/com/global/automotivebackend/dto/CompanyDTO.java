package com.global.automotivebackend.dto;


import lombok.Data;

@Data
public class CompanyDTO {

    private String createdBy;
    private String modifiedBy;
    private String companyId;
    private String companyName;
    private String companyAddress;
}