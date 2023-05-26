package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.DeviceHistorical;
import com.global.automotivebackend.repository.DeviceHistoricalRepository;
import com.global.automotivebackend.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceHistoricalRepository deviceHistoricalRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDevice(Integer id) {
        return deviceRepository.findById(id).get();
    }

    public List<DeviceHistorical> getDeviceHistorical() {
        return deviceHistoricalRepository.findAll();
    }

    public GenericResponse addDevice(Device device, LocalDateTime timestamp) {
        GenericResponse crudResponse = new GenericResponse();
        Device deviceToBeSaved = new Device(device.getDeviceId(), device.getDeviceType(), device.getDeviceName(), timestamp, timestamp, device.getCreatedBy(), device.getModifiedBy());
        DeviceHistorical deviceHistorical = new DeviceHistorical(UUID.randomUUID(), device.getDeviceId(), device.getDeviceType(), device.getDeviceName(), timestamp, timestamp, device.getCreatedBy(), device.getModifiedBy());
        deviceRepository.save(deviceToBeSaved);
        deviceHistoricalRepository.save(deviceHistorical);
        crudResponse.setMessage("Device with " + device.getDeviceId() + " is added");
        crudResponse.setStatus(true);
        return crudResponse;
    }

    public GenericResponse updateDevice(Device device, LocalDateTime modifiedTime) {

        GenericResponse crudResponse = new GenericResponse();
        System.out.println(device.getDeviceId());
        Optional<Device> searchedDevice = deviceRepository.findById(device.getDeviceId());
        if (searchedDevice.isPresent()) {
            Device foundDevices = searchedDevice.get();
            Device updateDevice = new Device(device.getDeviceId(), device.getDeviceType(), device.getDeviceName(), foundDevices.getCreatedTime(), modifiedTime, foundDevices.getCreatedBy(), device.getModifiedBy());
            DeviceHistorical deviceHistorical = new DeviceHistorical(UUID.randomUUID(), device.getDeviceId(), device.getDeviceType(), device.getDeviceName(), foundDevices.getCreatedTime(), modifiedTime, foundDevices.getCreatedBy(), device.getModifiedBy());
            deviceRepository.save(updateDevice);
            deviceHistoricalRepository.save(deviceHistorical);
            crudResponse.setMessage("Device with id " + device.getDeviceId() + " is updated");
            crudResponse.setStatus(true);
        } else {
            crudResponse.setMessage("Device with id " + device.getDeviceId() + " is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }

    public GenericResponse deleteDeviceById(Integer deviceId) {
        GenericResponse crudResponse = new GenericResponse();
        Optional<Device> device = deviceRepository.findById(deviceId);
        if (device.isPresent()) {
            deviceRepository.deleteById(deviceId);
            crudResponse.setMessage("Device with id " + deviceId + " is deleted");
            crudResponse.setStatus(true);
        } else {
            crudResponse.setMessage("Device with id " + deviceId + " is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }
}
