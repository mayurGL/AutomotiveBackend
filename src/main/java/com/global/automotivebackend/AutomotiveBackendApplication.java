package com.global.automotivebackend;

import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.Gps;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.service.KafkaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class AutomotiveBackendApplication implements CommandLineRunner {

    @Autowired
    private KafkaMessageService messageService;

    private final Random random = new Random();

    private final List<Vehicle> vehicles = Arrays.asList(new Vehicle("1", "GL110", "Suzuki", "Dzire", 2017, null, null, "Mayur", "Mayur"), new Vehicle("2", "HCL237", "Honda", "Accord", 2020, null, null, "Mayur", "Mayur"), new Vehicle("3", "IBM404", "Hyundai", "Aura", 2023, null, null, "Mayur", "Mayur"), new Vehicle("4", "TCS965", "Toyota", "Innova", 2015, null, null, "Mayur", "Mayur"), new Vehicle("5", "INF202", "Tata", "Tiago", 2017, null, null, "Mayur", "Mayur"), new Vehicle("6", "EY365", "Kia", "Seltos", 2015, null, null, "Mayur", "Mayur"));

    private final List<Device> devices = Arrays.asList(new Device("1", "sensor", "temperature sensor", null, null, "Mayur", "Mayur"), new Device("2", "sensor", "voltage sensor", null, null, "Mayur", "Mayur"), new Device("3", "sensor", "parking sensor", null, null, "Mayur", "Mayur"));


    private final List<Company> companies = Arrays.asList(new Company("GL110", "GlobalLogic", "Noida", null, null, "Mayur", "Mayur"), new Company("HCL237", "HCL", "Chennai", null, null, "Mayur", "Mayur"), new Company("IBM404", "IBM", "Bangalore", null, null, "Mayur", "Mayur"), new Company("TCS925", "TCS", "Hyderabad", null, null, "Mayur", "Mayur"), new Company("INFO123", "Infosys", "Pune", null, null, "Mayur", "Mayur"), new Company("EY365", "EY", "Noida", null, null, "Mayur", "Mayur"));


    public static void main(String[] args) {
        SpringApplication.run(AutomotiveBackendApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

        for (Company data : companies) {
            data.setCreated_time(Instant.now().toString());
            data.setModified_time(Instant.now().toString());
            messageService.sendCompanyData(data);
        }

        for (Device data : devices) {
            data.setCreated_time(Instant.now().toString());
            data.setModified_time(Instant.now().toString());
            messageService.sendDeviceData(data);
        }

        for (Vehicle data : vehicles) {
            data.setCreated_time(Instant.now().toString());
            data.setModified_time(Instant.now().toString());
            data.setCompanyId(setRandomCompanyId());
            messageService.sendVehicleData(data);
        }

        while (true) {
            Gps gps = new Gps(Instant.now().toString(), getRandomVehicleId(), getRandomDeviceId(), setRandomCompanyId(), getRandomLatitude(), getRandomLongitude(), getRandomSpeed());
            messageService.sendGpsData(gps);
            TimeUnit.SECONDS.sleep(60);

        }

    }


    /**
     * Random latitude between 25 and 35 degree
     *
     * @return double value of latitude
     */
    private double getRandomLatitude() {
        return 25.0 + random.nextDouble() * 10.0;
    }


    /**
     * Random longitude between -120 and -130 degree
     *
     * @return double value of longitude
     */
    private double getRandomLongitude() {
        return -120.0 - random.nextDouble() * 10.0;
    }


    /**
     * Random speed between 0 and 100 km/h
     *
     * @return double value of speed
     */
    private double getRandomSpeed() {
        return random.nextDouble() * 100.0;
    }


    /**
     * Random company Ids for vehicles
     *
     * @return String value of company Ids
     */
    private String setRandomCompanyId() {

        List<String> companies = Arrays.asList("GL110", "HCL237", "IBM404", "TCS925", "INFY202", "EY365");
        return companies.get(random.nextInt(companies.size()));
    }

    /**
     * Random device IDs for gps
     *
     * @return
     */
    public String getRandomDeviceId() {

        List<String> deviceIds = Arrays.asList("1", "2", "3");
        return deviceIds.get(random.nextInt(deviceIds.size()));
    }

    public String getRandomVehicleId() {

        List<String> vehicleIds = Arrays.asList("1", "2", "3", "4", "5", "6");
        return vehicleIds.get(random.nextInt(vehicleIds.size()));
    }
}