package com.global.automotivebackend.controller;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.model.VehicleHistorical;
import com.global.automotivebackend.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/all")
    public List<Vehicle> findAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/get/{id}")
    public Vehicle findVehicle(@PathVariable Integer id) {
        return vehicleService.getVehicle(id);
    }

    @GetMapping("/historical")
    public List<VehicleHistorical> findVehicleHistorical() {
        return vehicleService.getVehicleHistorical();
    }

    @PostMapping("/add")
    public CrudResponse addVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.addVehicle(vehicle, LocalDateTime.now());
    }

    @PutMapping("/update")
    public CrudResponse updateVehicle(@Valid @RequestBody Vehicle vehicle) {
        return vehicleService.updateVehicle(vehicle, LocalDateTime.now());
    }

    @DeleteMapping("/delete/{id}")
    public CrudResponse deleteVehicle(@PathVariable Integer id) {
        return vehicleService.deleteVehicleById(id);
    }

}