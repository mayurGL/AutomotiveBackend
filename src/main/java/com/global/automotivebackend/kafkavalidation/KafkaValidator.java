package com.global.automotivebackend.kafkavalidation;

import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.model.Vehicle;
import org.springframework.stereotype.Component;

/*
 * Class to help in validating data incoming from kafka broker
 * This is done to help with null data being sent from kafka broker.
 */
@Component
public class KafkaValidator {

    public boolean isValidCompany(Company company) {
        return company != null
                && company.getCompanyId() != null
                && company.getCompanyName() != null
                && company.getCompanyAddress() != null
                && company.getCreatedTime() != null
                && company.getModifiedTime() != null
                && company.getCreatedBy() != null
                && company.getModifiedBy() != null;
    }


    public boolean isValidGps(Gps gps) {
        return gps != null
                && gps.getCreatedTime() != null
                && gps.getVehicleId() != null
                && gps.getDeviceId() != null
                && gps.getCompanyId() != null
                && gps.getLatitude() != null
                && gps.getLongitude() != null
                && gps.getSpeed() != null;
    }

    public boolean isValidDevice(Device device) {

        return device != null
                && device.getDeviceId() != null
                && device.getDeviceType() != null
                && device.getDeviceName() != null
                && device.getCreatedTime() != null
                && device.getModifiedTime() != null
                && device.getCreatedBy() != null
                && device.getModifiedBy() != null;
    }

    public boolean isValidVehicle(Vehicle vehicle) {

        return vehicle != null
                && vehicle.getVehicleId() != null
                && vehicle.getCompanyId() != null
                && vehicle.getMake() != null
                && vehicle.getModel() != null
                && vehicle.getYear() != null
                && vehicle.getCreatedTime() != null
                && vehicle.getModifiedTime() != null
                && vehicle.getCreatedBy() != null
                && vehicle.getModifiedBy() != null;
    }

}
