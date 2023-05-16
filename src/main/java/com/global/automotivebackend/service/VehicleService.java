package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {
    public List<Vehicle> getAllVehicles();

    public CrudResponse addVehicle(Vehicle vehicle, String timeStatus);

    public CrudResponse updateVehicle(Vehicle vehicle, String modified_time);

    public CrudResponse deleteVehicleById(String vehicleId);
}
