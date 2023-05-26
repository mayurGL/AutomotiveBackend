package com.global.automotivebackend.controller.secured;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.service.DeviceService;
import com.global.automotivebackend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/device")
public class SecuredDeviceController {

    @Autowired
    private DeviceService deviceService;


    @PostMapping("/add")
    public ResponseEntity<GenericResponse> addDevice(@Valid @RequestBody Device device, HttpServletRequest request) {

        String token = CookieToJwtConverter.getTokenFromCookie(request);

        if(token == null || JwtUtil.validateToken(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login",false));
        }

        User user = JwtUtil.validateToken(token);

        device.setCreatedBy(user.getUsername());
        device.setModifiedBy(user.getUsername());

        return ResponseEntity.ok(deviceService.addDevice(device, LocalDateTime.now()));
    }

    @PutMapping("/update")
    public ResponseEntity<GenericResponse> updateDevice(@Valid @RequestBody Device device, HttpServletRequest request) {

        String token = CookieToJwtConverter.getTokenFromCookie(request);

        if(token == null || JwtUtil.validateToken(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login",false));
        }

        User user = JwtUtil.validateToken(token);

        device.setModifiedBy(user.getUsername());
        return ResponseEntity.ok(deviceService.updateDevice(device, LocalDateTime.now()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteDevice(@PathVariable Integer id, HttpServletRequest request) {

        String token = CookieToJwtConverter.getTokenFromCookie(request);

        if(token == null || JwtUtil.validateToken(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login",false));
        }
        return ResponseEntity.ok(deviceService.deleteDeviceById(id));
    }
}
