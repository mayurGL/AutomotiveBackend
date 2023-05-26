package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.model.VehicleHistorical;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface VehicleService {
    public List<Vehicle> getAllVehicles();

    public Vehicle getVehicle(Integer id);

    public List<VehicleHistorical> getVehicleHistorical();

    public GenericResponse addVehicle(Vehicle vehicle, LocalDateTime timestamp);

    public GenericResponse updateVehicle(Vehicle vehicle, LocalDateTime modifiedTime);

    public GenericResponse deleteVehicleById(Integer vehicleId);

}