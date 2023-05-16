package com.global.automotivebackend.service;

import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.model.Vehicle;
import org.springframework.stereotype.Service;

@Service
public interface KafkaMessageService {
    public void sendCompanyData(Company companyData);

    public void sendVehicleData(Vehicle vehicleData);

    public void sendGpsData(Gps gpsData);

    public void sendDeviceData(Device deviceData);
}
