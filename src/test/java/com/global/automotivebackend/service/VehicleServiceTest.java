package com.global.automotivebackend.service;

import com.global.automotivebackend.advice.IdAlreadyExistsException;
import com.global.automotivebackend.advice.IdNotFoundException;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.model.VehicleHistorical;
import com.global.automotivebackend.repository.VehicleHistoricalRepository;
import com.global.automotivebackend.repository.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleHistoricalRepository vehicleHistoricalRepository;

    private VehicleService vehicleService;

    @BeforeEach
    void setUp() {
        vehicleService= new VehicleServiceImpl(vehicleRepository,vehicleHistoricalRepository);
    }

    @Test
    void testGetAllVehicles() {

        vehicleService.getAllVehicles();
        verify(vehicleRepository).findAll();
    }

    @Test
    void testGetVehiclesHistorical(){

        vehicleService.getVehicleHistorical();
        verify(vehicleHistoricalRepository).findAll();
    }

    @Test
    public void testGetVehicleByIdExists() {

        int vehicleId=5;

       Vehicle vehicle =   new Vehicle(5, 6, "XYZ Car", "GHI Company", 2015, LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");

       when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));


        Vehicle result = vehicleService.getVehicle(vehicleId);


        assertNotNull(result);
        assertEquals(5, result.getVehicleId());
        assertEquals("XYZ Car", result.getMake());


        verify(vehicleRepository).findById(vehicleId);
    }

    @Test
    public void testGetCompanyByIdNotExists() {

        int randomVehicleId=90;

        when(vehicleRepository.findById(anyInt())).thenReturn(Optional.empty());


        assertThrows(IdNotFoundException.class, () -> vehicleService.getVehicle(randomVehicleId));


        verify(vehicleRepository).findById(randomVehicleId);
    }




    @Test
    void testAddVehicleSuccess() {

        Vehicle vehicle =   new Vehicle(5, 6, "XYZ Car", "GHI Company", 2015, LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");
        GenericResponse testCase1 = new GenericResponse();
        testCase1.setMessage("Vehicle with " + vehicle.getVehicleId() + " is added");
        testCase1.setStatus(true);

        when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.empty());

        GenericResponse vehicleAdded = vehicleService.addVehicle(vehicle,LocalDateTime.now());

        assertThat(vehicleAdded).isEqualTo(testCase1);


    }

    @Test
    void testAddVehicleWithExistingId() {

        Vehicle vehicle =   new Vehicle(1, 2, "DEF Car", "LOL Company", 2019, LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");

        LocalDateTime timestamp = LocalDateTime.now();

        when(vehicleRepository.findById(vehicle.getVehicleId())).thenReturn(Optional.of(vehicle));


        IdAlreadyExistsException exception = assertThrows(IdAlreadyExistsException.class, () -> vehicleService.addVehicle(vehicle, timestamp));


        assertEquals("Vehicle with this ID already exists", exception.getMessage());


    }


    @Test
    public void testDeleteCompanyByIdSuccess() {

        int vehicleId = 1;
        Vehicle vehicle =   new Vehicle(1, 2, "DEF Car", "LOL Company", 2019, LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(vehicle));


        GenericResponse response = vehicleService.deleteVehicleById(vehicleId);


        assertNotNull(response);
        assertTrue(response.isStatus());
        assertEquals("Vehicle with id " + vehicleId + " is deleted", response.getMessage());


        verify(vehicleRepository).findById(vehicleId);
        verify(vehicleRepository).deleteById(vehicleId);
    }

    @Test
    public void testDeleteCompanyByIdNotFound() {

        int vehicleId = 10;
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());


        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> vehicleService.deleteVehicleById(vehicleId));


        assertEquals("Vehicle with this ID doesn't exists", exception.getMessage());

    }

    @Test
    public void testUpdateVehicleSuccess() {

        int vehicleId = 1;
        LocalDateTime modifiedTime = LocalDateTime.now();
        Vehicle existedVehicle =   new Vehicle(1, 2, "DEF Car", "LOL Company", 2019, LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");
        Vehicle updatedVehicle =   new Vehicle(1, 9, "GFG Car", "XGL Company", 2019, existedVehicle.getCreatedTime(), modifiedTime, existedVehicle.getCreatedBy(), "Person A");

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(existedVehicle));
        when(vehicleRepository.save(updatedVehicle)).thenReturn(updatedVehicle);
        when(vehicleHistoricalRepository.save(any(VehicleHistorical.class))).thenReturn(null);


        GenericResponse response = vehicleService.updateVehicle(updatedVehicle, modifiedTime);


        assertNotNull(response);
        assertTrue(response.isStatus());
        assertEquals("Vehicle with id " + vehicleId + " is updated", response.getMessage());


        verify(vehicleRepository).findById(vehicleId);
        verify(vehicleRepository).save(updatedVehicle);
        verify(vehicleHistoricalRepository).save(any(VehicleHistorical.class));
    }

    @Test
    public void testUpdateVehicleNotFound() {

        int vehicleId = 3;
        Vehicle updatedVehicle =   new Vehicle(3, 5, "LMO Car", "ABC Company", 2017, LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");
        LocalDateTime modifiedTime = LocalDateTime.now();
        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());


        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> vehicleService.updateVehicle(updatedVehicle, modifiedTime));


        assertEquals("Vehicle with this ID doesn't exists", exception.getMessage());

    }









}
