package com.global.automotivebackend.service;

import com.global.automotivebackend.advice.IdAlreadyExistsException;
import com.global.automotivebackend.advice.IdNotFoundException;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.DeviceHistorical;
import com.global.automotivebackend.repository.DeviceHistoricalRepository;
import com.global.automotivebackend.repository.DeviceRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/*
 * DeviceService implementation class
 */
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceHistoricalRepository deviceHistoricalRepository;

    private static final Logger logger = Logger.getLogger(DeviceServiceImpl.class);

    public DeviceServiceImpl(DeviceRepository deviceRepository, DeviceHistoricalRepository deviceHistoricalRepository) {
        this.deviceRepository = deviceRepository;
        this.deviceHistoricalRepository = deviceHistoricalRepository;
    }

    /*
     * Method to get list of all devices
     */
    public List<Device> getAllDevices() {
        logger.info("@GET - Print all devices");
        return deviceRepository.findAll();
    }

    /*
     * Method to get device by ID
     */
    public Device getDevice(Integer id) {
        Optional<Device> deviceToBeFound = deviceRepository.findById(id);
        if (deviceToBeFound.isEmpty()) {
            logger.error("@GET - Device with ID: " + id + " doesn't exist");
            throw new IdNotFoundException("Device with ID: " + id + " doesn't exist");
        } else {
            logger.info("@GET - Print device with ID: " + id);
            return deviceToBeFound.get();
        }
    }

    /*
     * Method to get historical data of device
     */
    public List<DeviceHistorical> getDeviceHistorical() {
        logger.info("@GET - Print historical data of device");
        return deviceHistoricalRepository.findAll();
    }

    /*
     * Method to add device
     */
    public GenericResponse addDevice(Device device, LocalDateTime timestamp) {
        GenericResponse crudResponse = new GenericResponse();
        Device deviceToBeSaved = new Device(device.getDeviceId(), device.getDeviceType(), device.getDeviceName(), timestamp, timestamp, device.getCreatedBy(), device.getModifiedBy());
        DeviceHistorical deviceHistorical = new DeviceHistorical(UUID.randomUUID(), device.getDeviceId(), device.getDeviceType(), device.getDeviceName(), timestamp, timestamp, device.getCreatedBy(), device.getModifiedBy());
        if (deviceRepository.findById(deviceToBeSaved.getDeviceId()).isPresent()) {
            logger.error("@POST - Device with ID: " + device.getDeviceId() + " already exists");
            throw new IdAlreadyExistsException("Device with ID: " + device.getDeviceId() + " already exists");
        } else {
            deviceRepository.save(deviceToBeSaved);
            deviceHistoricalRepository.save(deviceHistorical);
            crudResponse.setMessage("Device with ID: " + device.getDeviceId() + " added");
            logger.info("@POST - Device with ID: " + device.getDeviceId() + " added");
            crudResponse.setStatus(true);
            return crudResponse;
        }
    }

    /*
     * Method to delete device
     */
    public GenericResponse deleteDeviceById(Integer deviceId) {
        GenericResponse crudResponse = new GenericResponse();
        Optional<Device> device = deviceRepository.findById(deviceId);
        if (device.isPresent()) {
            deviceRepository.deleteById(deviceId);
            crudResponse.setMessage("Device with ID: " + deviceId + " deleted");
            logger.info("@DELETE - Device with ID: " + deviceId + " deleted");
            crudResponse.setStatus(true);
        } else {
            logger.error("@DELETE - Device with ID: " + deviceId + " doesn't exist!!");
            throw new IdNotFoundException("Device with ID: " + deviceId + " doesn't exist!!");
        }
        return crudResponse;
    }

    /*
     * Method to update device
     */
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
            crudResponse.setMessage("Device with ID: " + device.getDeviceId() + " updated");
            logger.info("@PUT - Device with ID: " + device.getDeviceId() + " updated");
            crudResponse.setStatus(true);
        } else {
            logger.error("@PUT - Device with ID: " + device.getDeviceId() + " doesn't exist");
            throw new IdNotFoundException("Device with ID: " + device.getDeviceId() + " doesn't exist");
        }
        return crudResponse;
    }
}