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
public class DeviceHistorical {
    @PrimaryKey
    private UUID id;

    @Column(value = "device_id")
    @NotNull(message = "Enter a valid device ID!!")
    private Integer deviceId;

    @Column(value = "device_type")
    @NotNull(message = "Enter a device type!!")
    @NotBlank(message = "Enter a device type!!")
    private String deviceType;

    @Column(value = "device_name")
    @NotNull(message = "Enter the device name!!")
    @NotBlank(message = "Enter the device name!!")
    private String deviceName;

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