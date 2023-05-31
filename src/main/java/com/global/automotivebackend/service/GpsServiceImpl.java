package com.global.automotivebackend.service;

import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.repository.GpsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GpsServiceImpl implements GpsService {

    @Autowired
    private GpsRepository gpsRepository;

    public GpsServiceImpl(GpsRepository gpsRepository) {
        this.gpsRepository = gpsRepository;
    }

    public List<Gps> getAllGps() {

        return gpsRepository.findAll();
    }
}
