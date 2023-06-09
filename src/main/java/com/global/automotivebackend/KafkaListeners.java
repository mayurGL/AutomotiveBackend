package com.global.automotivebackend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.global.automotivebackend.kafkavalidation.KafkaValidator;
import com.global.automotivebackend.model.*;
import com.global.automotivebackend.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/*
 * Class for kafka listeners
 * to handle real-time data from kafka
 */
@Component
@AllArgsConstructor
public class KafkaListeners {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyHistoricalRepository companyHistoricalRepository;

    @Autowired
    private GpsRepository gpsRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceHistoricalRepository deviceHistoricalRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private VehicleHistoricalRepository vehicleHistoricalRepository;

    @Autowired
    private KafkaValidator kafkaValidator;

    @KafkaListener(topics = "companyTopic", groupId = "groupId")
    void companyTopicListener(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Company company = objectMapper.readValue(data, Company.class);
        if (kafkaValidator.isValidCompany(company)) {
            CompanyHistorical companyHistorical = new CompanyHistorical(UUID.randomUUID(), company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress(), company.getCreatedTime(), company.getModifiedTime(), company.getCreatedBy(), company.getModifiedBy());
            companyRepository.save(company);
            companyHistoricalRepository.save(companyHistorical);
        }
    }

    @KafkaListener(topics = "gpsTopic", groupId = "groupId")
    void gpsTopicListener(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Gps gps = objectMapper.readValue(data, Gps.class);
        if (kafkaValidator.isValidGps(gps)) {
            gpsRepository.save(gps);
        }
    }

    @KafkaListener(topics = "deviceTopic", groupId = "groupId")
    void deviceTopicListener(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Device device = objectMapper.readValue(data, Device.class);
        if (kafkaValidator.isValidDevice(device)) {
            DeviceHistorical deviceHistorical = new DeviceHistorical(UUID.randomUUID(), device.getDeviceId(), device.getDeviceType(), device.getDeviceName(), device.getCreatedTime(), device.getModifiedTime(), device.getCreatedBy(), device.getModifiedBy());
            deviceRepository.save(device);
            deviceHistoricalRepository.save(deviceHistorical);
        }
    }

    @KafkaListener(topics = "vehicleTopic", groupId = "groupId")
    void vehicleTopicListener(String data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Vehicle vehicle = objectMapper.readValue(data, Vehicle.class);
        if (kafkaValidator.isValidVehicle(vehicle)) {
            VehicleHistorical vehicleHistorical = new VehicleHistorical(UUID.randomUUID(), vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), vehicle.getCreatedTime(), vehicle.getModifiedTime(), vehicle.getCreatedBy(), vehicle.getModifiedBy());
            vehicleRepository.save(vehicle);
            vehicleHistoricalRepository.save(vehicleHistorical);
        }
    }
}