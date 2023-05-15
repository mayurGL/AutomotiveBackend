package com.global.automotivebackend.controller;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.service.DeviceService;
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
    public CrudResponse addDevice(@RequestBody Device device){
        return deviceService.addDevice(device, Instant.now().toString());
    }

    @PutMapping("/update")
    public CrudResponse updateDevice(@RequestBody Device device){
        return deviceService.updateDevice(device,Instant.now().toString());
    }

    @DeleteMapping("/delete")
    public CrudResponse deleteDevice(@RequestParam String id){
        return deviceService.deleteDeviceById(id);
    }
}
