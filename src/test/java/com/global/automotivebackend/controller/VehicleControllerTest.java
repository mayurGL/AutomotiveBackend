package com.global.automotivebackend.controller;


import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.model.VehicleHistorical;
import com.global.automotivebackend.service.VehicleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleControllerTest {

    @Mock
    private VehicleService vehicleService;

    private VehicleController vehicleController;

    @BeforeEach
    void setUp() {
        vehicleController = new VehicleController(vehicleService);
    }

    @Test
    public void testGetAllVehicles_ReturnsListOfVehicles() {

        List<Vehicle> expectedVehicles = Arrays.asList(
                new Vehicle(3, 2, "Honda", "Model G", 2017, LocalDateTime.now(), LocalDateTime.now(), "person s", "person j"),

                new Vehicle(5, 1, "Tata", "Model w", 2019, LocalDateTime.now(), LocalDateTime.now(), "person g", "person k")
        );
        when(vehicleService.getAllVehicles()).thenReturn(expectedVehicles);


        List<Vehicle> actualVehicles = vehicleController.findAllVehicles();


        assertEquals(expectedVehicles.size(), actualVehicles.size());
        assertEquals(expectedVehicles.get(0), actualVehicles.get(0));
        assertEquals(expectedVehicles.get(1), actualVehicles.get(1));
    }

    @Test
    public void testGetAllVehicles_ReturnsEmptyList_WhenNoVehicles() {

        List<Vehicle> expectedVehicles = List.of();
        when(vehicleService.getAllVehicles()).thenReturn(expectedVehicles);


        List<Vehicle> actualVehicles = vehicleController.findAllVehicles();


        Assertions.assertTrue(actualVehicles.isEmpty());
    }

    @Test
    public void testGetVehicle_ReturnsVehicle_WhenValidId() {

        int vehicleId = 1;
        Vehicle expectedVehicle =  new Vehicle(1, 2, "Honda", "Model G", 2017, LocalDateTime.now(), LocalDateTime.now(), "person s", "person j");

        when(vehicleService.getVehicle(vehicleId)).thenReturn(expectedVehicle);


        Vehicle actualVehicle = vehicleController.findVehicle(vehicleId);


        Assertions.assertEquals(expectedVehicle, actualVehicle);
    }

    @Test
    public void testGetVehicle_ReturnsNull_WhenInvalidId() {

        int vehicleId = 99;
        when(vehicleService.getVehicle(vehicleId)).thenReturn(null);


        Vehicle actualVehicle = vehicleController.findVehicle(vehicleId);


        Assertions.assertNull(actualVehicle);
    }

    @Test
    public void testGetVehicle_ReturnsVehicleHistoricalList() {

        List<VehicleHistorical> expectedHistoricalData = Arrays.asList(
                new VehicleHistorical(UUID.randomUUID(), 2, 1, "suzuki","model a", 2019, LocalDateTime.now(), LocalDateTime.now(), "person A", "person S"),

                new VehicleHistorical(UUID.randomUUID(), 4, 5, "hyundai", "model b", 2015, LocalDateTime.now(), LocalDateTime.now(),"person A", "person I")
        );
        when(vehicleService.getVehicleHistorical()).thenReturn(expectedHistoricalData);


        List<VehicleHistorical> actualHistoricalData = vehicleController.findVehicleHistorical();


        Assertions.assertEquals(expectedHistoricalData.size(), actualHistoricalData.size());
        Assertions.assertEquals(expectedHistoricalData.get(0), actualHistoricalData.get(0));
        Assertions.assertEquals(expectedHistoricalData.get(1), actualHistoricalData.get(1));
    }

    @Test
    public void testGetVehicle_ReturnsEmptyList_WhenNoHistoricalData() {

        List<VehicleHistorical> expectedHistoricalData = List.of();
        when(vehicleService.getVehicleHistorical()).thenReturn(expectedHistoricalData);


        List<VehicleHistorical> actualHistoricalData = vehicleController.findVehicleHistorical();


        Assertions.assertTrue(actualHistoricalData.isEmpty());
    }


}
