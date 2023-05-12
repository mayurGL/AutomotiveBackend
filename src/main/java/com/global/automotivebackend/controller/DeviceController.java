package com.global.automotivebackend.controller;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.dto.DeviceDTO;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.service.DeviceService;
import com.global.automotivebackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/all")
    public List<Device> findAllDevices(){
        return deviceService.getAllDevices();
    }

    @PostMapping("/add")
    public CrudResponse addDevice(@RequestBody DeviceDTO device){
        return deviceService.addDevice(device, Instant.now());
    }

    @PutMapping("/update")
    public CrudResponse updateDevice(@RequestBody DeviceDTO device){
        return deviceService.updateDevice(device,Instant.now());
    }

    @DeleteMapping("/delete/{id}")
    public CrudResponse deleteDevice(@PathVariable String id){
        return deviceService.deleteDeviceById(id);
    }
}
