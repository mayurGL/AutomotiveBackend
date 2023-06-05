package com.global.automotivebackend.controller;

import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.service.GpsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 * public controller class to handle gps
 */
@RestController
@RequestMapping("/gps")
public class GpsController {

    @Autowired
    private GpsService gpsService;

    public GpsController(GpsService gpsService) {
        this.gpsService = gpsService;
    }

    /*
     * Method to get all gps data
     */
    @GetMapping("/all")
    @Operation(summary = "Get all GPS data", description = "This API endpoint gets all the gps records available.")
    public List<Gps> getAllGps() {
        return gpsService.getAllGps();
    }
}
