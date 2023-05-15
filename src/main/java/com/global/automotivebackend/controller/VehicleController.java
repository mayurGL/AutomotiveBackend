package com.global.automotivebackend.controller;


import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/all")
    public List<Vehicle> findAllVehicles(){
        return vehicleService.getAllVehicles();
    }

    @PostMapping("/add")
    public CrudResponse addVehicle(@RequestBody Vehicle vehicle){
       return vehicleService.addVehicle(vehicle, Instant.now().toString());
    }

    @PutMapping("/update")
    public CrudResponse updateVehicle(@RequestBody Vehicle vehicle){
       return vehicleService.updateVehicle(vehicle, Instant.now().toString());
    }

    @DeleteMapping("/delete")
    public CrudResponse deleteVehicle(@RequestParam String id){
        return vehicleService.deleteVehicleById(id);
    }


}
