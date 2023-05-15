package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public CrudResponse addVehicle(Vehicle vehicle, String timeStatus) {
        CrudResponse crudResponse = new CrudResponse();
        Vehicle vehicleToBeSaved = new Vehicle(timeStatus,timeStatus, vehicle.getCreatedBy(), vehicle.getModifiedBy(), vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear());
        vehicleRepository.save(vehicleToBeSaved);
        crudResponse.setMessage("Vehicle with " + vehicle.getVehicleId() + " is added");
        crudResponse.setStatus(true);
        return crudResponse;
    }

    public CrudResponse updateVehicle(Vehicle vehicle, String modified_time){

        CrudResponse crudResponse = new CrudResponse();
        Optional<List<Vehicle>> searchedVehicles = vehicleRepository.findByVehicleId(vehicle.getVehicleId());
        List<Vehicle> updateVehicles = new ArrayList<>();
        if (searchedVehicles.isPresent()){
            List<Vehicle> foundVehicles=searchedVehicles.get();

            for (Vehicle v: foundVehicles) {
                Vehicle updateVehicle= new Vehicle(v.getCreated_time(),modified_time, v.getCreatedBy(), vehicle.getModifiedBy(), vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear());
                updateVehicles.add(updateVehicle);
            }

            vehicleRepository.saveAll(updateVehicles);
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

        Optional<List<Vehicle>> vehicle = vehicleRepository.findByVehicleId(vehicleId);

        if(vehicle.isPresent())
        {
            for (Vehicle v:vehicle.get()) {
                vehicleRepository.deleteById(v.getCreated_time());
            }
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
