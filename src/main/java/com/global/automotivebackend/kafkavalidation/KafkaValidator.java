package com.global.automotivebackend.kafkavalidation;

import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.model.Vehicle;

public class KafkaValidator {

    public static boolean isValidCompany(Company company){
        return company!=null
                && company.getCompanyId()!=null
                && company.getCompanyName()!=null
                && company.getCompanyAddress()!=null
                && company.getCreatedTime()!=null
                && company.getModifiedTime()!=null
                && company.getCreatedBy()!=null
                && company.getModifiedBy()!=null;
    }


    public static boolean isValidGps(Gps gps){
        return gps!=null
                && gps.getCreatedTime()!=null
                && gps.getVehicleId()!=null
                && gps.getDeviceId()!=null
                && gps.getCompanyId()!=null
                && gps.getLatitude()!=null
                && gps.getLongitude()!=null
                && gps.getSpeed()!=null;
    }

    public static boolean isValidDevice(Device device){

        return device!=null
                && device.getDeviceId()!=null
                && device.getDeviceType()!=null
                && device.getDeviceName()!=null
                && device.getCreatedTime()!=null
                && device.getModifiedTime()!=null
                && device.getCreatedBy()!=null
                && device.getModifiedBy()!=null;
    }

    public static boolean isValidVehicle(Vehicle vehicle){

        return vehicle!=null
                && vehicle.getVehicleId()!=null
                && vehicle.getCompanyId()!=null
                && vehicle.getMake()!=null
                && vehicle.getModel()!=null
                && vehicle.getYear()!=null
                && vehicle.getCreatedTime()!=null
                && vehicle.getModifiedTime()!=null
                && vehicle.getCreatedBy()!=null
                && vehicle.getModifiedBy()!=null;
    }

}
