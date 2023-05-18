package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Vehicle;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface VehicleService {
    public List<Vehicle> getAllVehicles();

    public CrudResponse addVehicle(Vehicle vehicle, LocalDateTime timestamp);

    public CrudResponse updateVehicle(Vehicle vehicle, LocalDateTime modifiedTime);

    public CrudResponse deleteVehicleById(Integer vehicleId);
}
