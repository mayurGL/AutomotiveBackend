package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.model.VehicleHistorical;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Service interface for vehicle controller to handle
 * vehicle entity and vehicle historical entity
 */
@Service
public interface VehicleService {
    List<Vehicle> getAllVehicles();

    Vehicle getVehicle(Integer id);

    List<VehicleHistorical> getVehicleHistorical();

    GenericResponse addVehicle(Vehicle vehicle, LocalDateTime timestamp);

    GenericResponse updateVehicle(Vehicle vehicle, LocalDateTime modifiedTime);

    GenericResponse deleteVehicleById(Integer vehicleId);
}