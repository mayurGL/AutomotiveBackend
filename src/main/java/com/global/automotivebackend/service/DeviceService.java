package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
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

    public CrudResponse addDevice(Device device, LocalDateTime timestamp);

    public CrudResponse updateDevice(Device device, LocalDateTime modifiedTime);

    public CrudResponse deleteDeviceById(Integer deviceId);
}
