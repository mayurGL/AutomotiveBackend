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

	private final Random random= new Random();

	private final List<Vehicle> vehicles = Arrays.asList(
			new Vehicle(null,"Mayur","Mayur","1", "GL110", "Suzuki", "Dzire", 2017),
			new Vehicle(null,"Mayur","Mayur","2", "HCL237", "Honda", "Accord", 2020),
			new Vehicle(null,"Mayur","Mayur","3", "IBM404", "Hyundai", "Aura", 2023),
			new Vehicle(null,"Mayur","Mayur","4", "TCS925", "Toyota", "Innova", 2015),
			new Vehicle(null,"Mayur","Mayur","5", "INFY202", "Tata", "Tiago", 2017),
			new Vehicle(null,"Mayur","Mayur","6", "EY365", "Kia", "Seltos", 2019)
	);

	private final List<Device> devices = Arrays.asList(
			new Device(null,"Mayur","Mayur","1", "sensor", "temperature sensor"),
			new Device(null,"Mayur","Mayur","2", "sensor", "voltage sensor"),
			new Device(null,"Mayur","Mayur","3", "sensor", "parking sensor")
	);


	private final List<Company> companies = Arrays.asList(
			new Company(null,"Mayur","Mayur","GL110", "GlobalLogic", "Noida"),
			new Company(null,"Mayur","Mayur","HCL237", "HCL", "Chennai"),
			new Company(null,"Mayur","Mayur","IBM404", "IBM", "Bangalore"),
			new Company(null,"Mayur","Mayur","TCS925", "TCS", "Kolkata"),
			new Company(null,"Mayur","Mayur","INFY202", "INFOSYS", "Pune"),
			new Company(null,"Mayur","Mayur","EY365", "EY", "Noida")
	);


	public static void main(String[] args) {
		SpringApplication.run(AutomotiveBackendApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		for (Company data: companies) {
			data.setTimeStatus(Instant.now());
			messageService.sendCompanyData(data);
		}


		while (true){
			Vehicle vehicle = vehicles.get(random.nextInt(vehicles.size()));
			Device device = devices.get(random.nextInt(devices.size()));
			vehicle.setTimeStatus(Instant.now());
			vehicle.setCompanyId(setRandomCompanyId());
			device.setTimeStatus(Instant.now());
			Gps gps = new Gps(Instant.now(),vehicle.getVehicleId(),getRandomDeviceId(),vehicle.getCompanyId(), getRandomLatitude(), getRandomLongitude(), getRandomSpeed());

			messageService.sendGpsData(gps);
			messageService.sendDeviceData(device);
			messageService.sendVehicleData(vehicle);


			TimeUnit.SECONDS.sleep(6000);

		}


	}


	/**
	 * Random latitude between 25 and 35 degree
	 * @return double value of latitude
	 */
	private double getRandomLatitude(){
		return 25.0 + random.nextDouble() * 10.0;
	}


	/**
	 * Random longitude between -120 and -130 degree
	 * @return double value of longitude
	 */
	private double getRandomLongitude(){
		return -120.0 - random.nextDouble()*10.0;
	}


	/**
	 * Random speed between 0 and 100 km/h
	 * @return double value of speed
	 */
	private double getRandomSpeed(){
		return random.nextDouble() * 100.0;
	}


	/**
	 * Random company Ids for vehicles
	 * @return String value of company Ids
	 */
	private String setRandomCompanyId(){

		List<String> companies= Arrays.asList("GL110","HCL237","IBM404","TCS925","INFY202","EY365");
		return companies.get(random.nextInt(companies.size()));
	}

	/**
	 * Random device IDs for gps
	 * @return
	 */
	public String getRandomDeviceId(){

		List<String> deviceIds = Arrays.asList("1","2","3");
		return deviceIds.get(random.nextInt(deviceIds.size()));
	}



}
