package com.global.automotivebackend.dto;


import lombok.Data;

@Data
public class DeviceDTO {

    private String createdBy;
    private String modifiedBy;
    private String deviceId;
    private String deviceType;
    private String deviceName;
}
