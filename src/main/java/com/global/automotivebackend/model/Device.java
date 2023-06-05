package com.global.automotivebackend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/*
 * Entity Class for device table
 */
@Table
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @PrimaryKey(value = "device_id")
    @NotNull(message = "Enter a valid device ID!!")
    private Integer deviceId;

    @Column(value = "device_type")
    @NotNull(message = "Enter a device type!!")
    @NotBlank(message = "Enter a device type!!")
    private String deviceType;

    @Column(value = "device_name")
    @NotNull(message = "Enter a device name!!")
    @NotBlank(message = "Enter a device name!!")
    private String deviceName;

    @Column(value = "created_time")
    private LocalDateTime createdTime;

    @Column(value = "modified_time")
    private LocalDateTime modifiedTime;

    @Column(value = "created_by")
    private String createdBy;

    @Column(value = "modified_by")
    private String modifiedBy;
}