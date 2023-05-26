package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.DeviceHistorical;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface DeviceService {
    public List<Device> getAllDevices();

    public Device getDevice(Integer id);

    public List<DeviceHistorical> getDeviceHistorical();

    public GenericResponse addDevice(Device device, LocalDateTime timestamp);

    public GenericResponse updateDevice(Device device, LocalDateTime modifiedTime);

    public GenericResponse deleteDeviceById(Integer deviceId);
}
