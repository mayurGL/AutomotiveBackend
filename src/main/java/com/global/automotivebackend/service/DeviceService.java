package com.global.automotivebackend.service;


import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.dto.DeviceDTO;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public CrudResponse addDevice(DeviceDTO deviceDTO, Instant timeStatus) {
        CrudResponse crudResponse = new CrudResponse();
        Device deviceToBeSaved= new Device(timeStatus, deviceDTO.getCreatedBy(), deviceDTO.getModifiedBy(), deviceDTO.getDeviceId(), deviceDTO.getDeviceType(), deviceDTO.getDeviceName());
        deviceRepository.save(deviceToBeSaved);
        crudResponse.setMessage("Device with " + deviceDTO.getDeviceId() + " is added");
        crudResponse.setStatus(true);
        return crudResponse;
    }

    public CrudResponse updateDevice(DeviceDTO deviceDTO, Instant timeStatus){

        CrudResponse crudResponse = new CrudResponse();
        Optional<Device> searchedDevice = deviceRepository.findByDeviceId(deviceDTO.getDeviceId());

        if (searchedDevice.isPresent()){
           Device deviceToBeUpdated = new Device(timeStatus, deviceDTO.getCreatedBy(), deviceDTO.getModifiedBy(), deviceDTO.getDeviceId(), deviceDTO.getDeviceType(), deviceDTO.getDeviceName());
            deviceRepository.save(deviceToBeUpdated);
            crudResponse.setMessage("Device with "+deviceDTO.getDeviceId()+" is updated");
            crudResponse.setStatus(true);
        }

        else {
            crudResponse.setMessage("Device with "+deviceDTO.getDeviceId()+" is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }

    public CrudResponse deleteDeviceById(String deviceId) {

        CrudResponse crudResponse = new CrudResponse();

        Optional<Device> device = deviceRepository.findByDeviceId(deviceId);
        if(device.isPresent())
        {   deviceRepository.deleteByDeviceId(deviceId);
            crudResponse.setMessage("Device with "+deviceId+" is deleted");
            crudResponse.setStatus(true);
        }
        else {
            crudResponse.setMessage("Device with "+deviceId+" is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }
}
