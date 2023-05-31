package com.global.automotivebackend.service;

import com.global.automotivebackend.repository.GpsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GpsServiceTest {

    @Mock
    private GpsRepository gpsRepository;

    private GpsService gpsService;

    @BeforeEach
    void setUp() {
        gpsService = new GpsServiceImpl(gpsRepository);
    }

    @Test
    void testGetAllDevices() {

        gpsService.getAllGps();
        verify(gpsRepository).findAll();
    }
}
