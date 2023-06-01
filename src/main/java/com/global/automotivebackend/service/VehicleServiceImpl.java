package com.global.automotivebackend.service;

import com.global.automotivebackend.advice.IdAlreadyExistsException;
import com.global.automotivebackend.advice.IdNotFoundException;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.model.VehicleHistorical;
import com.global.automotivebackend.repository.VehicleHistoricalRepository;
import com.global.automotivebackend.repository.VehicleRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleHistoricalRepository vehicleHistoricalRepository;

    private static final Logger logger = Logger.getLogger(VehicleServiceImpl.class);

    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleHistoricalRepository vehicleHistoricalRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleHistoricalRepository = vehicleHistoricalRepository;
    }

    public List<Vehicle> getAllVehicles() {
        logger.info("@GET - Print all Vehicles");
        return vehicleRepository.findAll();
    }

    public Vehicle getVehicle(Integer id) {
        Optional<Vehicle> vehicleToBeFound = vehicleRepository.findById(id);
        if (vehicleToBeFound.isEmpty()){
            logger.error("@GET - Vehicle with ID: " + id + " doesn't exist");
            throw new IdNotFoundException("Vehicle with ID: " + id + " doesn't exist");
        }else {
            logger.info("@GET - Print vehicle with ID: " + id);
            return vehicleToBeFound.get();
        }
    }

    public List<VehicleHistorical> getVehicleHistorical() {
        logger.info("@GET - Print historical data of vehicle");
        return vehicleHistoricalRepository.findAll();
    }

    public GenericResponse addVehicle(Vehicle vehicle, LocalDateTime timestamp) {
        GenericResponse crudResponse = new GenericResponse();
        Vehicle vehicleToBeSaved = new Vehicle(vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), timestamp, timestamp, vehicle.getCreatedBy(), vehicle.getModifiedBy());
        VehicleHistorical vehicleHistorical = new VehicleHistorical(UUID.randomUUID(), vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), timestamp, timestamp, vehicle.getCreatedBy(), vehicle.getModifiedBy());
        if (vehicleRepository.findById(vehicleToBeSaved.getVehicleId()).isPresent()){
            logger.error("@POST - Vehicle with ID: " + vehicle.getVehicleId() + " already exists");
            throw new IdAlreadyExistsException("Vehicle with ID: " + vehicle.getVehicleId() + " already exists");
        }else {
            vehicleRepository.save(vehicleToBeSaved);
            vehicleHistoricalRepository.save(vehicleHistorical);
            crudResponse.setMessage("Vehicle with ID: " + vehicle.getVehicleId() + " added");
            logger.info("@POST - Vehicle with ID: " + vehicle.getVehicleId() + " added");
            crudResponse.setStatus(true);
            return crudResponse;
        }
    }

    public GenericResponse updateVehicle(Vehicle vehicle, LocalDateTime modifiedTime) {
        GenericResponse crudResponse = new GenericResponse();
        Optional<Vehicle> searchedVehicles = vehicleRepository.findById(vehicle.getVehicleId());
        if (searchedVehicles.isPresent()) {
            Vehicle foundVehicle = searchedVehicles.get();
            Vehicle updateVehicle = new Vehicle(vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), foundVehicle.getCreatedTime(), modifiedTime, foundVehicle.getCreatedBy(), vehicle.getModifiedBy());
            VehicleHistorical vehicleHistorical = new VehicleHistorical(UUID.randomUUID(), vehicle.getVehicleId(), vehicle.getCompanyId(), vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), foundVehicle.getCreatedTime(), modifiedTime, foundVehicle.getCreatedBy(), vehicle.getModifiedBy());
            vehicleRepository.save(updateVehicle);
            vehicleHistoricalRepository.save(vehicleHistorical);
            crudResponse.setMessage("Vehicle with id " + vehicle.getVehicleId() + " updated");
            logger.info("@PUT - Vehicle with ID: " + vehicle.getVehicleId() + " updated");
            crudResponse.setStatus(true);
        } else {
            logger.error("@PUT - Vehicle with ID: " + vehicle.getVehicleId() + " doesn't exist");
            throw new IdNotFoundException("Vehicle with ID: " + vehicle.getVehicleId() + " doesn't exist");
        }
        return crudResponse;
    }

    public GenericResponse deleteVehicleById(Integer vehicleId) {
        GenericResponse crudResponse = new GenericResponse();
        Optional<Vehicle> vehicle = vehicleRepository.findById(vehicleId);
        if (vehicle.isPresent()) {
            vehicleRepository.deleteById(vehicleId);
            crudResponse.setMessage("Vehicle with ID: " + vehicleId + " deleted");
            logger.info("@DELETE - Vehicle with ID: " + vehicleId + " deleted");
            crudResponse.setStatus(true);
        } else {
            logger.error("@DELETE - Vehicle with ID: " + vehicleId + " doesn't exist!!");
            throw new IdNotFoundException("Vehicle with ID: " + vehicleId + " doesn't exist!!");
        }
        return crudResponse;
    }
}
