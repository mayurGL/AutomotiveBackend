package com.global.automotivebackend.controller;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.DeviceHistorical;
import com.global.automotivebackend.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/all")
    public List<Device> findAllDevices() {
        return deviceService.getAllDevices();
    }

    @GetMapping("/get/{id}")
    public Device findDevice(@PathVariable Integer id) {
        return deviceService.getDevice(id);
    }

    @GetMapping("/historical")
    public List<DeviceHistorical> findDeviceHistorical() {
        return deviceService.getDeviceHistorical();
    }

}
