package com.global.automotivebackend.service;

import com.global.automotivebackend.model.*;
import com.global.automotivebackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    @Autowired
    private CompanyHistoricalRepository companyHistoricalRepository;

    @Autowired
    private VehicleHistoricalRepository vehicleHistoricalRepository;

    @Autowired
    private DeviceHistoricalRepository deviceHistoricalRepository;

    public void sendCompanyData(Company companyData) {
        CompanyHistorical companyHistorical = new CompanyHistorical(UUID.randomUUID(), companyData.getCompany_id(), companyData.getCompanyName(), companyData.getCompanyAddress(), companyData.getCreated_time(),companyData.getModified_time(), companyData.getCreatedBy(), companyData.getModifiedBy());
        kafkaTemplate.send(companyTopic,companyData);
        companyRepository.save(companyData);
        companyHistoricalRepository.save(companyHistorical);
    }

    public void sendVehicleData(Vehicle vehicleData) {
        VehicleHistorical vehicleHistorical = new VehicleHistorical(UUID.randomUUID(), vehicleData.getVehicle_id(), vehicleData.getCompanyId(), vehicleData.getMake(), vehicleData.getModel(), vehicleData.getYear(), vehicleData.getCreated_time(),vehicleData.getModified_time(), vehicleData.getCreatedBy(), vehicleData.getModifiedBy());
        kafkaTemplate.send(vehicleTopic,vehicleData);
        vehicleRepository.save(vehicleData);
        vehicleHistoricalRepository.save(vehicleHistorical);
    }

    public void sendGpsData(Gps gpsData) {
        kafkaTemplate.send(gpsTopic,gpsData);
        gpsRepository.save(gpsData);
    }

    public void sendDeviceData(Device deviceData) {
        DeviceHistorical deviceHistorical = new DeviceHistorical(UUID.randomUUID(), deviceData.getDevice_id(), deviceData.getDeviceType(), deviceData.getDeviceName(), deviceData.getCreated_time(), deviceData.getModified_time(),deviceData.getCreatedBy(), deviceData.getModifiedBy());
        kafkaTemplate.send(deviceTopic,deviceData);
        deviceRepository.save(deviceData);
        deviceHistoricalRepository.save(deviceHistorical);
    }
}
