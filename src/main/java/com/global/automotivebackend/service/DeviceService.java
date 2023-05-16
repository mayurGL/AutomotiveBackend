package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Device;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeviceService {
    public List<Device> getAllDevices();

    public CrudResponse addDevice(Device device, String timeStatus);

    public CrudResponse updateDevice(Device device, String modified_time);

    public CrudResponse deleteDeviceById(String deviceId);
}
