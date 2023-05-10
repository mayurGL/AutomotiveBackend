package com.global.automotivebackend;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.global.automotivebackend.model.CompanyData;
import com.global.automotivebackend.model.DeviceData;
import com.global.automotivebackend.model.GpsData;
import com.global.automotivebackend.model.VehicleData;
import com.global.automotivebackend.service.KafkaMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaExampleApplication implements CommandLineRunner {

	@Autowired
	private KafkaMessageService messageService;

	private final Random random= new Random();

	private final List<VehicleData> vehicleDataList = Arrays.asList(
			new VehicleData("1", "GL110", "Suzuki", "Dzire", 2017),
			new VehicleData("2", "HCL237", "Honda", "Accord", 2020),
			new VehicleData("3", "IBM404", "Hyundai", "Aura", 2023),
			new VehicleData("4", "TCS925", "Toyota", "Innova", 2015),
			new VehicleData("5", "INFY202", "Tata", "Tiago", 2017),
			new VehicleData("6", "EY365", "Kia", "Seltos", 2019)
	);

	private final List<DeviceData> deviceDataList = Arrays.asList(
			new DeviceData("1", "sensor", "temperature sensor"),
			new DeviceData("2", "sensor", "voltage sensor"),
			new DeviceData("3", "sensor", "parking sensor")
	);


	private final List<CompanyData> companyDataList = Arrays.asList(
			new CompanyData("GL110", "GlobalLogic", "Noida"),
			new CompanyData("HCL237", "HCL", "Chennai"),
			new CompanyData("IBM404", "IBM", "Bangalore"),
			new CompanyData("TCS925", "TCS", "Kolkata"),
			new CompanyData("INFY202", "INFOSYS", "Pune"),
			new CompanyData("EY365", "EY", "Noida")
	);


	public static void main(String[] args) {
		SpringApplication.run(KafkaExampleApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		for (CompanyData data: companyDataList) {
			messageService.sendCompanyData(data);
		}


		while (true){
			VehicleData vehicleData = vehicleDataList.get(random.nextInt(vehicleDataList.size()));
			DeviceData deviceData = deviceDataList.get(random.nextInt(deviceDataList.size()));
			GpsData gpsData = new GpsData(Uuids.timeBased(),vehicleData.getVehicleId(), vehicleData.getCompanyId(), getRandomLatitude(), getRandomLongitude(), getRandomSpeed());

			messageService.sendGpsData(gpsData);
			messageService.sendDeviceData(deviceData);
			messageService.sendVehicleData(vehicleData);


			TimeUnit.SECONDS.sleep(25);

		}


	}


	/**
	 * Random latitude between 25 and 35 degree
	 * @return double latitude
	 */
	private double getRandomLatitude(){
		return 25.0 + random.nextDouble() * 10.0;
	}

	/**
	 * Random longitude between -120 and -130 degree
	 * @return double longitude
	 */
	private double getRandomLongitude(){
		return -120.0 - random.nextDouble()*10.0;
	}

	/**
	 * Random speed between 0 and 100 km/h
	 * @return
	 */
	private double getRandomSpeed(){
		return random.nextDouble() * 100.0;
	}

}
