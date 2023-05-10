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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageService {

    private final String companyTopic="companyTopic";
    private final String vehicleTopic="vehicleTopic";
    private final String gpsTopic="gpsTopic";
    private final String deviceTopic="deviceTopic";

    @Autowired
    private CompanyTopicRepository companyTopicRepository;
    @Autowired
    private DeviceTopicRepository deviceTopicRepository;
    @Autowired
    private GpsTopicRepository gpsTopicRepository;
    @Autowired
    private VehicleTopicRepository vehicleTopicRepository;
    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    public void sendCompanyData(CompanyData companyData) {
        kafkaTemplate.send(companyTopic,companyData);
       companyTopicRepository.save(companyData);
    }

    public void sendVehicleData(VehicleData vehicleData) {
        kafkaTemplate.send(vehicleTopic,vehicleData);
        vehicleTopicRepository.save(vehicleData);
    }

    public void sendGpsData(GpsData gpsData) {
        kafkaTemplate.send(gpsTopic,gpsData);
        gpsTopicRepository.save(gpsData);
    }

    public void sendDeviceData(DeviceData deviceData) {
        kafkaTemplate.send(deviceTopic,deviceData);
        deviceTopicRepository.save(deviceData);
    }
}
