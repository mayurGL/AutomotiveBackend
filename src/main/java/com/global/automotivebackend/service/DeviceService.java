package com.global.automotivebackend.service;


import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.DeviceHistorical;
import com.global.automotivebackend.repository.DeviceHistoricalRepository;
import com.global.automotivebackend.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceHistoricalRepository deviceHistoricalRepository;
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public CrudResponse addDevice(Device device, String timeStatus) {
        CrudResponse crudResponse = new CrudResponse();
        Device deviceToBeSaved = new Device(device.getDeviceId(), device.getDeviceType(), device.getDeviceName(), timeStatus, timeStatus, device.getCreatedBy(), device.getModifiedBy());
        DeviceHistorical deviceHistorical = new DeviceHistorical(UUID.randomUUID(), device.getDeviceId(), device.getDeviceType(), device.getDeviceName(), timeStatus, timeStatus, device.getCreatedBy(), device.getModifiedBy());
        deviceRepository.save(deviceToBeSaved);
        deviceHistoricalRepository.save(deviceHistorical);
        crudResponse.setMessage("Device with " + device.getDeviceId() + " is added");
        crudResponse.setStatus(true);
        return crudResponse;
    }

    public CrudResponse updateDevice(Device device, String modified_time){

        CrudResponse crudResponse = new CrudResponse();
        Optional<Device> searchedDevice = deviceRepository.findById(device.getDeviceId());
        if (searchedDevice.isPresent()){
            Device foundDevices = searchedDevice.get();
            Device updateDevice = new Device(device.getDeviceId(), device.getDeviceType(), device.getDeviceName(), foundDevices.getCreated_time(), modified_time,foundDevices.getCreatedBy(), device.getModifiedBy());
            DeviceHistorical deviceHistorical = new DeviceHistorical(UUID.randomUUID(), device.getDeviceId(), device.getDeviceType(), device.getDeviceName(), foundDevices.getCreated_time(), modified_time,foundDevices.getCreatedBy(), device.getModifiedBy());
            deviceRepository.save(updateDevice);
            deviceHistoricalRepository.save(deviceHistorical);
            crudResponse.setMessage("Device with id "+device.getDeviceId()+" is updated");
            crudResponse.setStatus(true);
        }
        else {
            crudResponse.setMessage("Device with id "+device.getDeviceId()+" is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }

    public CrudResponse deleteDeviceById(String deviceId) {
        CrudResponse crudResponse = new CrudResponse();
        Optional<Device> device = deviceRepository.findById(deviceId);
        if(device.isPresent())
        {
            deviceRepository.deleteById(deviceId);
            crudResponse.setMessage("Device with id "+deviceId+" is deleted");
            crudResponse.setStatus(true);
        }
        else {
            crudResponse.setMessage("Device with id "+deviceId+" is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }
}
