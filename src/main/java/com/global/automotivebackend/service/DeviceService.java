package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.DeviceHistorical;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Service interface for device controller to handle
 * device entity and device historical entity
 */
@Service
public interface DeviceService {
    List<Device> getAllDevices();

    Device getDevice(Integer id);

    List<DeviceHistorical> getDeviceHistorical();

    GenericResponse addDevice(Device device, LocalDateTime timestamp);

    GenericResponse updateDevice(Device device, LocalDateTime modifiedTime);

    GenericResponse deleteDeviceById(Integer deviceId);
}
