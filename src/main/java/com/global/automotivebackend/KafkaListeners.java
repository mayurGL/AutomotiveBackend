package com.global.automotivebackend;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.model.Vehicle;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "companyTopic", groupId = "groupId")
    void companyTopicListener(String data) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Company company = objectMapper.readValue(data, Company.class);
    }

    @KafkaListener(topics = "gpsTopic", groupId = "groupId")
    void gpsTopicListener(String data) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Gps gps = objectMapper.readValue(data, Gps.class);
    }

    @KafkaListener(topics = "deviceTopic", groupId = "groupId")
    void deviceTopicListener(String data) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Device device = objectMapper.readValue(data, Device.class);
    }

    @KafkaListener(topics = "vehicleTopic", groupId = "groupId")
    void vehicleTopicListener(String data) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        Vehicle vehicle = objectMapper.readValue(data, Vehicle.class);
    }
}
