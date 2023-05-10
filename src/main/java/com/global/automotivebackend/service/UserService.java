package com.global.automotivebackend.service;

import com.global.automotivebackend.model.CompanyData;
import com.global.automotivebackend.model.DeviceData;
import com.global.automotivebackend.model.GpsData;
import com.global.automotivebackend.model.VehicleData;
import com.global.automotivebackend.repository.CompanyTopicRepository;
import com.global.automotivebackend.repository.DeviceTopicRepository;
import com.global.automotivebackend.repository.GpsTopicRepository;
import com.global.automotivebackend.repository.VehicleTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    CompanyTopicRepository companyTopicRepository;
    @Autowired
    DeviceTopicRepository deviceTopicRepository;

    @Autowired
    VehicleTopicRepository vehicleTopicRepository;

    @Autowired
    GpsTopicRepository gpsTopicRepository;
    public List<CompanyData> getAllCompanies() {
        return companyTopicRepository.findAll();
    }

    public List<VehicleData> getAllVehicles() {
        return vehicleTopicRepository.findAll();
    }

    public List<DeviceData> getAllDevices() {
        return deviceTopicRepository.findAll();
    }

    public List<GpsData> getGpsData() {
        return gpsTopicRepository.findAll();
    }

    public void addCompany(CompanyData companyData) {
        companyTopicRepository.save(companyData);
    }

    public void addVehicle(VehicleData vehicleData) {
        vehicleTopicRepository.save(vehicleData);
    }

    public void addDevice(DeviceData deviceData) {
        deviceTopicRepository.save(deviceData);
    }

    public void deleteCompany(CompanyData companyData) {
        companyTopicRepository.delete(companyData);
    }

    public void deleteVehicle(VehicleData vehicleData) {
        vehicleTopicRepository.delete(vehicleData);
    }

    public void deleteDevice(DeviceData deviceData) {
        deviceTopicRepository.delete(deviceData);
    }
}
