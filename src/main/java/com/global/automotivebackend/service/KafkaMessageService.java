package com.global.automotivebackend.service;

import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.repository.CompanyRepository;
import com.global.automotivebackend.repository.DeviceRepository;
import com.global.automotivebackend.repository.GpsRepository;
import com.global.automotivebackend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageService {

    private final String companyTopic="companyTopic";
    private final String vehicleTopic="vehicleTopic";
    private final String gpsTopic="gpsTopic";
    private final String deviceTopic="deviceTopic";

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private GpsRepository gpsRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void sendCompanyData(Company companyData) {
        kafkaTemplate.send(companyTopic,companyData);
       companyRepository.save(companyData);
    }

    public void sendVehicleData(Vehicle vehicleData) {
        kafkaTemplate.send(vehicleTopic,vehicleData);
        vehicleRepository.save(vehicleData);
    }

    public void sendGpsData(Gps gpsData) {
        kafkaTemplate.send(gpsTopic,gpsData);
        gpsRepository.save(gpsData);
    }

    public void sendDeviceData(Device deviceData) {
        kafkaTemplate.send(deviceTopic,deviceData);
        deviceRepository.save(deviceData);
    }
}
