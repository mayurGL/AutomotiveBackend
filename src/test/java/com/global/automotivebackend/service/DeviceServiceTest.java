package com.global.automotivebackend.service;

import com.global.automotivebackend.advice.IdAlreadyExistsException;
import com.global.automotivebackend.advice.IdNotFoundException;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.DeviceHistorical;
import com.global.automotivebackend.repository.DeviceHistoricalRepository;
import com.global.automotivebackend.repository.DeviceRepository;
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
public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private DeviceHistoricalRepository deviceHistoricalRepository;

    private DeviceService deviceService;

    @BeforeEach
    void setUp() {
        deviceService = new DeviceServiceImpl(deviceRepository,deviceHistoricalRepository);
    }


    @Test
    void testGetAllDevices() {

        deviceService.getAllDevices();
        verify(deviceRepository).findAll();
    }

    @Test
    void testGetDeviceHistorical(){

        deviceService.getDeviceHistorical();
        verify(deviceHistoricalRepository).findAll();
    }

    @Test
    public void testGetDeviceByIdExists() {

        int deviceId=3;

       Device device = new Device(3, "sensor", "parking sensor", null, null, "Mayur", "Mayur");

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));


        Device result = deviceService.getDevice(deviceId);


        assertNotNull(result);
        assertEquals(3, result.getDeviceId());
        assertEquals("parking sensor", result.getDeviceName());


        verify(deviceRepository).findById(deviceId);
    }

    @Test
    public void testGetDeviceByIdNotExists() {

        int randomDeviceId=90;

        when(deviceRepository.findById(anyInt())).thenReturn(Optional.empty());


        assertThrows(IdNotFoundException.class, () -> deviceService.getDevice(randomDeviceId));


        verify(deviceRepository).findById(randomDeviceId);
    }




    @Test
    void testAddDeviceSuccess() {

        Device device = new Device(3, "sensor", "parking sensor", null, null, "Mayur", "Mayur");
        GenericResponse testCase1 = new GenericResponse();
        testCase1.setMessage("Device with id " + device.getDeviceId() + " is added");
        testCase1.setStatus(true);

        when(deviceRepository.findById(device.getDeviceId())).thenReturn(Optional.empty());

        GenericResponse deviceAdded = deviceService.addDevice(device,LocalDateTime.now());

        assertThat(deviceAdded).isEqualTo(testCase1);


    }

    @Test
    void testAddDeviceWithExistingId() {

        Device device = new Device(3, "sensor", "parking sensor", null, null, "Mayur", "Mayur");

        LocalDateTime timestamp = LocalDateTime.now();

        when(deviceRepository.findById(device.getDeviceId())).thenReturn(Optional.of(device));


        IdAlreadyExistsException exception = assertThrows(IdAlreadyExistsException.class, () -> deviceService.addDevice(device, timestamp));


        assertEquals("Device with this ID already exists", exception.getMessage());


    }


    @Test
    public void testDeleteDeviceByIdSuccess() {

        int deviceId = 2;
        Device device = new Device(2, "sensor", "temperature sensor", null, null, "Mayur", "Mayur");

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));


        GenericResponse response = deviceService.deleteDeviceById(deviceId);


        assertNotNull(response);
        assertTrue(response.isStatus());
        assertEquals("Device with id " + deviceId + " is deleted", response.getMessage());


        verify(deviceRepository).findById(deviceId);
        verify(deviceRepository).deleteById(deviceId);
    }

    @Test
    public void testDeleteDeviceByIdNotFound() {

        int deviceId = 10;
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());


        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> deviceService.deleteDeviceById(deviceId));


        assertEquals("Device with this ID doesn't exists", exception.getMessage());

    }

    @Test
    public void testUpdateDeviceSuccess() {

        int deviceId = 2;
        LocalDateTime modifiedTime = LocalDateTime.now();
        Device existedDevice = new Device(2, "sensor", "temperature sensor", LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");

        Device updatedDevice = new Device(2, "sensor", "fuel sensor", existedDevice.getCreatedTime(), modifiedTime, existedDevice.getCreatedBy(), "Person X");


        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(existedDevice));
        when(deviceRepository.save(updatedDevice)).thenReturn(updatedDevice);
        when(deviceHistoricalRepository.save(any(DeviceHistorical.class))).thenReturn(null);


        GenericResponse response = deviceService.updateDevice(updatedDevice, modifiedTime);


        assertNotNull(response);
        assertTrue(response.isStatus());
        assertEquals("Device with id " + deviceId + " is updated", response.getMessage());


        verify(deviceRepository).findById(deviceId);
        verify(deviceRepository).save(updatedDevice);
        verify(deviceHistoricalRepository).save(any(DeviceHistorical.class));
    }

    @Test
    public void testUpdateDeviceNotFound() {

        int deviceId = 3;
        Device updatedDevice = new Device(3, "sensor", "temperature sensor", LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");
        LocalDateTime modifiedTime = LocalDateTime.now();
        when(deviceRepository.findById(deviceId)).thenReturn(Optional.empty());


        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> deviceService.updateDevice(updatedDevice, modifiedTime));


        assertEquals("Device with this ID doesn't exists", exception.getMessage());

    }

}
