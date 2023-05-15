package com.global.automotivebackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Table
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Device {
    @PrimaryKey
    private String created_time;

    private String modified_time;

    private String createdBy;

    private String modifiedBy;

    @Column(value = "device_id")
    private String deviceId;

    @Column(value = "device_type")
    private String deviceType;

    @Column(value = "device_name")
    private String deviceName;
}
