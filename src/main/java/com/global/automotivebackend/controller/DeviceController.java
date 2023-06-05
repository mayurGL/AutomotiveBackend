package com.global.automotivebackend.controller;

import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.DeviceHistorical;
import com.global.automotivebackend.service.DeviceService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * public controller class to handle device
 */
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /*
     * Method to get all devices
     */
    @GetMapping("/all")
    @Operation(summary = "Get all device", description = "This API endpoint gets all the device records available, else it will return an empty list if no devices are available")
    public List<Device> findAllDevices() {
        return deviceService.getAllDevices();
    }

    /*
     * Method to get device by ID
     */
    @GetMapping("/get/{id}")
    @Operation(summary = "Get a device by an ID", description = "This API endpoint gets a device based on a unique ID available, else it will return a message of ID not found")
    public Device findDevice(@PathVariable Integer id) {
        return deviceService.getDevice(id);
    }

    /*
     * Method to get historical device data
     */
    @GetMapping("/historical")
    @Operation(summary = "Get all historical records of devices", description = "This API endpoint gets all historical events occurred in the device table and returns all the records")
    public List<DeviceHistorical> findDeviceHistorical() {
        return deviceService.getDeviceHistorical();
    }
}