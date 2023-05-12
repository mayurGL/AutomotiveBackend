package com.global.automotivebackend.dto;


import lombok.Data;

@Data
public class VehicleDTO {

    private String createdBy;
    private String modifiedBy;
    private String vehicleId;
    private String companyId;
    private String make;
    private String model;
    private int year;
}
