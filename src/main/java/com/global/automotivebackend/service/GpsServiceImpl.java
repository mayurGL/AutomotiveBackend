package com.global.automotivebackend.service;

import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.repository.GpsRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * GpsService implementation class
 */
@Service
public class GpsServiceImpl implements GpsService {

    @Autowired
    private GpsRepository gpsRepository;

    private static final Logger logger = Logger.getLogger(GpsServiceImpl.class);

    public GpsServiceImpl(GpsRepository gpsRepository) {
        this.gpsRepository = gpsRepository;
    }

    /*
     * Method to get list of all Gps data
     */
    public List<Gps> getAllGps() {
        logger.info("@GET - Print all GPS data");
        return gpsRepository.findAll();
    }
}
