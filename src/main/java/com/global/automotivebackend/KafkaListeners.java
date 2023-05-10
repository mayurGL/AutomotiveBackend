package com.global.automotivebackend;


import com.global.automotivebackend.model.CompanyData;
import com.global.automotivebackend.model.DeviceData;
import com.global.automotivebackend.model.GpsData;
import com.global.automotivebackend.model.VehicleData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "companyTopic", groupId = "groupId")
    void companyTopicListener(String data) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        CompanyData companyData = objectMapper.readValue(data, CompanyData.class);
    }

    @KafkaListener(topics = "gpsTopic", groupId = "groupId")
    void gpsTopicListener(String data) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        GpsData gpsData = objectMapper.readValue(data, GpsData.class);
    }

    @KafkaListener(topics = "deviceTopic", groupId = "groupId")
    void deviceTopicListener(String data) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        DeviceData deviceData = objectMapper.readValue(data, DeviceData.class);
    }

    @KafkaListener(topics = "vehicleTopic", groupId = "groupId")
    void vehicleTopicListener(String data) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        VehicleData vehicleData = objectMapper.readValue(data,VehicleData.class);
    }
}
