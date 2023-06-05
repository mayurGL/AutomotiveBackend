package com.global.automotivebackend.service;

import com.global.automotivebackend.model.Gps;

import java.util.List;

/*
 * Service interface for gps controller to handle GPS entity
 */
public interface GpsService {
    List<Gps> getAllGps();
}
