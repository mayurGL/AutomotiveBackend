package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.model.VehicleHistorical;
import com.global.automotivebackend.repository.VehicleHistoricalRepository;
import com.global.automotivebackend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleHistoricalRepository vehicleHistoricalRepository;
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public CrudResponse addVehicle(Vehicle vehicle, String timeStatus) {
        CrudResponse crudResponse = new CrudResponse();
        Vehicle vehicleToBeSaved = new Vehicle(vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), timeStatus,timeStatus, vehicle.getCreatedBy(), vehicle.getModifiedBy());
        VehicleHistorical vehicleHistorical = new VehicleHistorical(UUID.randomUUID(), vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), timeStatus,timeStatus, vehicle.getCreatedBy(), vehicle.getModifiedBy());
        vehicleRepository.save(vehicleToBeSaved);
        vehicleHistoricalRepository.save(vehicleHistorical);
        crudResponse.setMessage("Vehicle with " + vehicle.getVehicleId() + " is added");
        crudResponse.setStatus(true);
        return crudResponse;
    }

    public CrudResponse updateVehicle(Vehicle vehicle, String modified_time){

        CrudResponse crudResponse = new CrudResponse();
        Optional<Vehicle> searchedVehicles = vehicleRepository.findById(vehicle.getVehicleId());
        if (searchedVehicles.isPresent()){
            Vehicle foundVehicle = searchedVehicles.get();
            Vehicle updateVehicle = new Vehicle(vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(),foundVehicle.getCreated_time(), modified_time, foundVehicle.getCreatedBy(), vehicle.getModifiedBy());
            VehicleHistorical vehicleHistorical = new VehicleHistorical(UUID.randomUUID(), vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(),foundVehicle.getCreated_time(), modified_time, foundVehicle.getCreatedBy(), vehicle.getModifiedBy());
            vehicleRepository.save(updateVehicle);
            vehicleHistoricalRepository.save(vehicleHistorical);
            crudResponse.setMessage("Vehicle with id "+vehicle.getVehicleId()+" is updated");
            crudResponse.setStatus(true);
        }
        else {
            crudResponse.setMessage("Vehicle with id "+vehicle.getVehicleId()+" is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }

    public CrudResponse deleteVehicleById(String vehicleId) {
        CrudResponse crudResponse = new CrudResponse();
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        if(vehicle.isPresent())
        {
            vehicleRepository.deleteById(vehicleId);
            crudResponse.setMessage("Vehicle with id "+vehicleId+" is deleted");
            crudResponse.setStatus(true);
        }
        else {
            crudResponse.setMessage("Vehicle with id "+vehicleId+" is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }
}
