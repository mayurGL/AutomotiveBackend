package com.global.automotivebackend.controller;

import com.global.automotivebackend.model.CompanyData;
import com.global.automotivebackend.model.DeviceData;
import com.global.automotivebackend.model.GpsData;
import com.global.automotivebackend.model.VehicleData;
import com.global.automotivebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/allCompanies")
    public List<CompanyData> findAllCompanies(){
        List<CompanyData> companies = userService.getAllCompanies();
        return companies;
    }

    @GetMapping("/allVehicles")
    public List<VehicleData> findAllVehicles(){
        List<VehicleData> vehicles = userService.getAllVehicles();
        return vehicles;
    }

    @GetMapping("/allDevices")
    public List<DeviceData> findAllDevices(){
        List<DeviceData> devices = userService.getAllDevices();
        return devices;
    }

    @GetMapping("/gpsData")
    public List<GpsData> gpsData(){
        List<GpsData> data = userService.getGpsData();
        return data;
    }

    @PostMapping("/addCompany")
    public String addCompany(@RequestBody CompanyData companyData){
        userService.addCompany(companyData);
        return "Company Added Successfully";
    }

    @PostMapping("/addVehicle")
    public String addVehicle(@RequestBody VehicleData vehicleData){
        userService.addVehicle(vehicleData);
        return "Vehicle Added Successfully";
    }

    @PostMapping("/addDevice")
    public String addDevice(@RequestBody DeviceData deviceData){
        userService.addDevice(deviceData);
        return "Device Added Successfully";
    }

    @PutMapping("/updateCompany")
    public String updateCompany(@RequestBody CompanyData companyData){
        userService.addCompany(companyData);
        return "Company Updated Successfully";
    }

    @PutMapping("/updateVehicle")
    public String updateVehicle(@RequestBody VehicleData vehicleData){
        userService.addVehicle(vehicleData);
        return "Vehicle Updated Successfully";
    }

    @PutMapping("/updateDevice")
    public String updateDevice(@RequestBody DeviceData deviceData){
        userService.addDevice(deviceData);
        return "Device Updated Successfully";
    }

    @DeleteMapping("/deleteCompany")
    public String deleteCompany(@RequestBody CompanyData companyData){
        userService.deleteCompany(companyData);
        return "Company Updated Successfully";
    }

    @DeleteMapping("/deleteVehicle")
    public String deleteVehicle(@RequestBody VehicleData vehicleData){
        userService.deleteVehicle(vehicleData);
        return "Vehicle Updated Successfully";
    }

    @DeleteMapping("/updateDevice")
    public String deleteDevice(@RequestBody DeviceData deviceData){
        userService.deleteDevice(deviceData);
        return "Device Updated Successfully";
    }

}
