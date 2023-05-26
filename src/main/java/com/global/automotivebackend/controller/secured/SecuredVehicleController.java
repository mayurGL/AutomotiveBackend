package com.global.automotivebackend.controller.secured;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.service.VehicleService;
import com.global.automotivebackend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/vehicle")
public class SecuredVehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/add")
    public ResponseEntity<GenericResponse> addVehicle(@Valid @RequestBody Vehicle vehicle, HttpServletRequest request) {

        String token = CookieToJwtConverter.getTokenFromCookie(request);

        if(token == null || JwtUtil.validateToken(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login",false));
        }

        User user = JwtUtil.validateToken(token);

        vehicle.setCreatedBy(user.getUsername());
        vehicle.setModifiedBy(user.getUsername());
        return ResponseEntity.ok(vehicleService.addVehicle(vehicle, LocalDateTime.now()));
    }

    @PutMapping("/update")
    public ResponseEntity<GenericResponse> updateVehicle(@Valid @RequestBody Vehicle vehicle, HttpServletRequest request) {

        String token = CookieToJwtConverter.getTokenFromCookie(request);

        if(token == null || JwtUtil.validateToken(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login",false));
        }

        User user = JwtUtil.validateToken(token);

        vehicle.setModifiedBy(user.getUsername());
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicle, LocalDateTime.now()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<GenericResponse> deleteVehicle(@PathVariable Integer id, HttpServletRequest request) {

        String token = CookieToJwtConverter.getTokenFromCookie(request);

        if(token == null || JwtUtil.validateToken(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login",false));
        }

        return ResponseEntity.ok(vehicleService.deleteVehicleById(id));
    }
}
