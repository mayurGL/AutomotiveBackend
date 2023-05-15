package com.global.automotivebackend.service;


import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public CrudResponse addDevice(Device device, String timeStatus) {
        CrudResponse crudResponse = new CrudResponse();
        Device deviceToBeSaved = new Device(timeStatus,timeStatus, device.getCreatedBy(), device.getModifiedBy(), device.getDeviceId(), device.getDeviceType(), device.getDeviceName());
        deviceRepository.save(deviceToBeSaved);
        crudResponse.setMessage("Device with " + device.getDeviceId() + " is added");
        crudResponse.setStatus(true);
        return crudResponse;
    }

    public CrudResponse updateDevice(Device device, String modified_time){

        CrudResponse crudResponse = new CrudResponse();
        Optional<List<Device>> searchedDevice = deviceRepository.findByDeviceId(device.getDeviceId());
        List<Device> updateDevices = new ArrayList<>();
        if (searchedDevice.isPresent()){

            List<Device> foundDevices = searchedDevice.get();
            for (Device d: foundDevices) {

                Device updateDevice= new Device(d.getCreated_time(), modified_time,d.getCreatedBy(), device.getModifiedBy(), device.getDeviceId(), device.getDeviceType(), device.getDeviceName());
                updateDevices.add(updateDevice);
            }

            deviceRepository.saveAll(updateDevices);
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

        Optional<List<Device>> device = deviceRepository.findByDeviceId(deviceId);
        if(device.isPresent())
        {
            for (Device d: device.get()) {
                deviceRepository.deleteById(d.getCreated_time());
            }

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
