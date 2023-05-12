package com.global.automotivebackend.controller;


import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.service.GpsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gps")
public class GpsController {

    @Autowired
    private GpsService gpsService;

    @GetMapping("/all")
    public List<Gps> getAllGps(){
       return gpsService.getAllGps();
    }
}
