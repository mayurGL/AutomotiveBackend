package com.global.automotivebackend.controller;

import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.CompanyHistorical;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.DeviceHistorical;
import com.global.automotivebackend.service.DeviceService;
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
public class DeviceControllerTest {

    @Mock
    private DeviceService deviceService;

    private DeviceController deviceController;

    @BeforeEach
    void setUp() {
        deviceController = new DeviceController(deviceService);
    }

    @Test
    public void testGetAllDevices_ReturnsListOfDevices() {

        List<Device> expectedDevices = Arrays.asList(
                new Device(3, "sensor", "parking sensor", LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur"),

                new Device(2, "sensor", "temperature sensor", LocalDateTime.now(), LocalDateTime.now(), "person B", "person u")
        );
        when(deviceService.getAllDevices()).thenReturn(expectedDevices);


        List<Device> actualCompanies = deviceController.findAllDevices();


        assertEquals(expectedDevices.size(), actualCompanies.size());
        assertEquals(expectedDevices.get(0), actualCompanies.get(0));
        assertEquals(expectedDevices.get(1), actualCompanies.get(1));
    }

    @Test
    public void testGetAllDevices_ReturnsEmptyList_WhenNoDevices() {

        List<Device> expectedDevices = List.of();
        when(deviceService.getAllDevices()).thenReturn(expectedDevices);


        List<Device> actualDevices = deviceController.findAllDevices();


        Assertions.assertTrue(actualDevices.isEmpty());
    }

    @Test
    public void testGetDevice_ReturnsDevice_WhenValidId() {

        int deviceId = 1;
        Device expectedDevice = new Device(1, "sensor", "parking sensor", null, null, "Mayur", "Mayur");

        when(deviceService.getDevice(deviceId)).thenReturn(expectedDevice);


        Device actualDevice = deviceController.findDevice(deviceId);


        Assertions.assertEquals(expectedDevice, actualDevice);
    }

    @Test
    public void testGetDevice_ReturnsNull_WhenInvalidId() {

        int deviceId = 99;
        when(deviceService.getDevice(deviceId)).thenReturn(null);


        Device actualDevice = deviceController.findDevice(deviceId);


        Assertions.assertNull(actualDevice);
    }

    @Test
    public void testGetDevice_ReturnsDeviceHistoricalList() {

        List<DeviceHistorical> expectedHistoricalData = Arrays.asList(
                new DeviceHistorical(UUID.randomUUID(), 2, "sensor", "parking sensor", LocalDateTime.now(),LocalDateTime.now(), "Person Z", "Person Y"),
                new DeviceHistorical(UUID.randomUUID(), 4, "sensor", "temperature sensor", LocalDateTime.now(), LocalDateTime.now(), "Person C", "Person F")
        );
        when(deviceService.getDeviceHistorical()).thenReturn(expectedHistoricalData);


        List<DeviceHistorical> actualHistoricalData = deviceController.findDeviceHistorical();


        Assertions.assertEquals(expectedHistoricalData.size(), actualHistoricalData.size());
        Assertions.assertEquals(expectedHistoricalData.get(0), actualHistoricalData.get(0));
        Assertions.assertEquals(expectedHistoricalData.get(1), actualHistoricalData.get(1));
    }

    @Test
    public void testGetDevice_ReturnsEmptyList_WhenNoHistoricalData() {

        List<DeviceHistorical> expectedHistoricalData = List.of();
        when(deviceService.getDeviceHistorical()).thenReturn(expectedHistoricalData);


        List<DeviceHistorical> actualHistoricalData = deviceController.findDeviceHistorical();


        Assertions.assertTrue(actualHistoricalData.isEmpty());
    }
}
