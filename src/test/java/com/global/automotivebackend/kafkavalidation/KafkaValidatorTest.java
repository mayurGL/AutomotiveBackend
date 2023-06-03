package com.global.automotivebackend.kafkavalidation;

import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.model.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class KafkaValidatorTest {

    @Autowired
    private KafkaValidator kafkaValidator;

    @Test
    public void testIsValidCompany_ValidCompany() {
        // Arrange
        Company company = new Company();
        company.setCompanyId(1);
        company.setCompanyName("Test Company");
        company.setCompanyAddress("Test Address");
        company.setCreatedTime(LocalDateTime.now());
        company.setModifiedTime(LocalDateTime.now());
        company.setCreatedBy("Test User");
        company.setModifiedBy("Test User");


        // Act
        boolean isValid = kafkaValidator.isValidCompany(company);

        // Assert
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testIsValidCompany_NullCompany() {
        // Arrange
        Company company = null;

        // Act
        boolean isValid = kafkaValidator.isValidCompany(company);

        // Assert
        Assertions.assertFalse(isValid);
    }

    @Test
    public void testIsValidCompany_MissingFields() {
        // Arrange
        Company company = new Company();

        // Act
        boolean isValid = kafkaValidator.isValidCompany(company);

        // Assert
        Assertions.assertFalse(isValid);
    }



    @Test
    public void testIsValidGps_ValidGps() {
        // Arrange
        Gps gps = new Gps();
        gps.setCreatedTime(LocalDateTime.now());
        gps.setVehicleId(2);
        gps.setDeviceId(4);
        gps.setCompanyId(6);
        gps.setLatitude(12.345);
        gps.setLongitude(67.890);
        gps.setSpeed(50.0);


        // Act
        boolean isValid = kafkaValidator.isValidGps(gps);

        // Assert
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testIsValidGps_NullGps() {
        // Arrange
        Gps gps = null;

        // Act
        boolean isValid = kafkaValidator.isValidGps(gps);

        // Assert
        Assertions.assertFalse(isValid);
    }

    @Test
    public void testIsValidGps_MissingFields() {
        // Arrange
        Gps gps = new Gps();

        // Act
        boolean isValid = kafkaValidator.isValidGps(gps);

        // Assert
        Assertions.assertFalse(isValid);
    }

    @Test
    public void testIsValidDevice_ValidDevice() {
        // Arrange
        Device device = new Device();
        device.setDeviceId(5);
        device.setDeviceType("Type");
        device.setDeviceName("Test Device");
        device.setCreatedTime(LocalDateTime.now());
        device.setModifiedTime(LocalDateTime.now());
        device.setCreatedBy("Test User");
        device.setModifiedBy("Test User");


        // Act
        boolean isValid = kafkaValidator.isValidDevice(device);

        // Assert
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testIsValidDevice_NullDevice() {
        // Arrange
        Device device = null;

        // Act
        boolean isValid = kafkaValidator.isValidDevice(device);

        // Assert
        Assertions.assertFalse(isValid);
    }

    @Test
    public void testIsValidDevice_MissingFields() {
        // Arrange
        Device device = new Device();

        // Act
        boolean isValid = kafkaValidator.isValidDevice(device);

        // Assert
        Assertions.assertFalse(isValid);
    }

    @Test
    public void testIsValidVehicle_ValidVehicle() {
        // Arrange
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(3);
        vehicle.setCompanyId(4);
        vehicle.setMake("Toyota");
        vehicle.setModel("Camry");
        vehicle.setYear(2022);
        vehicle.setCreatedTime(LocalDateTime.now());
        vehicle.setModifiedTime(LocalDateTime.now());
        vehicle.setCreatedBy("Test User");
        vehicle.setModifiedBy("Test User");



        // Act
        boolean isValid = kafkaValidator.isValidVehicle(vehicle);

        // Assert
        Assertions.assertTrue(isValid);
    }

    @Test
    public void testIsValidVehicle_NullVehicle() {
        // Arrange
        Vehicle vehicle = null;


        // Act
        boolean isValid = kafkaValidator.isValidVehicle(vehicle);

        // Assert
        Assertions.assertFalse(isValid);
    }

    @Test
    public void testIsValidVehicle_MissingFields() {
        // Arrange
        Vehicle vehicle = new Vehicle();


        // Act
        boolean isValid = kafkaValidator.isValidVehicle(vehicle);

        // Assert
        Assertions.assertFalse(isValid);
    }




}
