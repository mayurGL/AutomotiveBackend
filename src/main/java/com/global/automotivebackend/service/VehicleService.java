package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.dto.VehicleDTO;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public CrudResponse addVehicle(VehicleDTO vehicle, Instant timeStatus) {
        CrudResponse crudResponse = new CrudResponse();
        Vehicle vehicleToBeSaved = new Vehicle(timeStatus, vehicle.getCreatedBy(), vehicle.getModifiedBy(), vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear());
        vehicleRepository.save(vehicleToBeSaved);
        crudResponse.setMessage("Vehicle with " + vehicle.getVehicleId() + " is added");
        crudResponse.setStatus(true);
        return crudResponse;
    }

    public CrudResponse updateVehicle(VehicleDTO vehicle, Instant timeStatus){

        CrudResponse crudResponse = new CrudResponse();
        Optional<Vehicle> searchedVehicle = vehicleRepository.findByVehicleId(vehicle.getVehicleId());

        if (searchedVehicle.isPresent()){
            Vehicle vehicleToBeUpdated= new Vehicle(timeStatus, vehicle.getCreatedBy(), vehicle.getModifiedBy(), vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear());
            vehicleRepository.save(vehicleToBeUpdated);
            crudResponse.setMessage("Vehicle with "+vehicle.getCompanyId()+" is updated");
            crudResponse.setStatus(true);
        }

        else {
            crudResponse.setMessage("Vehicle with "+vehicle.getCompanyId()+" is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }

    public CrudResponse deleteVehicleById(String vehicleId) {

        CrudResponse crudResponse = new CrudResponse();

        Optional<Vehicle> vehicle = vehicleRepository.findByVehicleId(vehicleId);
        if(vehicle.isPresent())
        {   vehicleRepository.deleteByVehicleId(vehicleId);
            crudResponse.setMessage("Vehicle with "+vehicleId+" is deleted");
            crudResponse.setStatus(true);
        }
        else {
            crudResponse.setMessage("Vehicle with "+vehicleId+" is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }
}
