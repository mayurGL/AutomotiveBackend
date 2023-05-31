package com.global.automotivebackend.controller;

import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.service.GpsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GpsControllerTest {

    @Mock
    private GpsService gpsService;

    private GpsController gpsController;

    @BeforeEach
    void setUp() {
        gpsController = new GpsController(gpsService);
    }

    @Test
    public void testGetAllGps_ReturnsListOfGps() {

        List<Gps> expectedGps = Arrays.asList(
                new Gps(LocalDateTime.now(), 2, 3, 6, 27.67, 89.12, 70.0),
                new Gps(LocalDateTime.now(), 1, 4, 5, 32.65, 43.232, 55.0)
        );
        when(gpsService.getAllGps()).thenReturn(expectedGps);


        List<Gps> actualGps = gpsController.getAllGps();


        assertEquals(expectedGps.size(), actualGps.size());
        assertEquals(expectedGps.get(0), actualGps.get(0));
        assertEquals(expectedGps.get(1), actualGps.get(1));
    }

    @Test
    public void testGetAllGps_ReturnsEmptyList_WhenNoGps() {

        List<Gps> expectedVehicles = List.of();
        when(gpsService.getAllGps()).thenReturn(expectedVehicles);


        List<Gps> actualGps = gpsController.getAllGps();


        Assertions.assertTrue(actualGps.isEmpty());
    }


}
