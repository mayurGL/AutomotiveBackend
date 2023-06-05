package com.global.automotivebackend.controller;

import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.model.VehicleHistorical;
import com.global.automotivebackend.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * public controller class to handle vehicle
 */
@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    /*
     * Method to get all vehicles
     */
    @GetMapping("/all")
    @Operation(summary = "Get all vehicle", description = "This API endpoint gets all the vehicle records available, else it will return an empty list if no vehicles are available")
    public List<Vehicle> findAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    /*
     * Method to get vehicle by ID
     */
    @GetMapping("/get/{id}")
    @Operation(summary = "Get a vehicle by an ID", description = "This API endpoint gets a vehicle based on a unique ID available, else it will return a message of ID not found")
    public Vehicle findVehicle(@PathVariable Integer id) {
        return vehicleService.getVehicle(id);
    }

    /*
     * Method to get historical vehicle data
     */
    @GetMapping("/historical")
    @Operation(summary = "Get all historical records of vehicles", description = "This API endpoint gets all historical events occurred in the vehicle table and returns all the records")
    public List<VehicleHistorical> findVehicleHistorical() {
        return vehicleService.getVehicleHistorical();
    }
}