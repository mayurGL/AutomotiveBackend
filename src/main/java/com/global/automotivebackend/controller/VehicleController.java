package com.global.automotivebackend.controller;

import com.global.automotivebackend.dto.GenericResponse;
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

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

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



}