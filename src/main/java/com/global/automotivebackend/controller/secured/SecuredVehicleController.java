package com.global.automotivebackend.controller.secured;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.model.Vehicle;
import com.global.automotivebackend.service.VehicleService;
import com.global.automotivebackend.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/*
 * secured controller class to handle vehicle
 */
@RestController
@RequestMapping("/vehicle")
public class SecuredVehicleController {

    @Autowired
    private VehicleService vehicleService;

    /*
     * method to add vehicle
     */
    @PostMapping("/add")
    @Operation(summary = "Add a vehicle", description = "This API endpoint adds a vehicle, if vehicle with same ID exists it throws exception.")
    public ResponseEntity<GenericResponse> addVehicle(@Valid @RequestBody Vehicle vehicle, HttpServletRequest request) {
        String token = CookieToJwtConverter.getTokenFromCookie(request);
        if (token == null || JwtUtil.validateToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login", false));
        }
        User user = JwtUtil.validateToken(token);
        vehicle.setCreatedBy(user.getUsername());
        vehicle.setModifiedBy(user.getUsername());
        return ResponseEntity.ok(vehicleService.addVehicle(vehicle, LocalDateTime.now()));
    }

    /*
     * method to update vehicle
     */
    @PutMapping("/update")
    @Operation(summary = "Update a vehicle", description = "This API endpoint updates a vehicle, if vehicle with the ID doesn't exist it throws exception.")
    public ResponseEntity<GenericResponse> updateVehicle(@Valid @RequestBody Vehicle vehicle, HttpServletRequest request) {
        String token = CookieToJwtConverter.getTokenFromCookie(request);
        if (token == null || JwtUtil.validateToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login", false));
        }
        User user = JwtUtil.validateToken(token);
        vehicle.setModifiedBy(user.getUsername());
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicle, LocalDateTime.now()));
    }

    /*
     * method to delete vehicle
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a vehicle", description = "This API endpoint deletes a vehicle, if vehicle with the ID doesn't exist it throws exception.")
    public ResponseEntity<GenericResponse> deleteVehicle(@PathVariable Integer id, HttpServletRequest request) {
        String token = CookieToJwtConverter.getTokenFromCookie(request);
        if (token == null || JwtUtil.validateToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login", false));
        }
        return ResponseEntity.ok(vehicleService.deleteVehicleById(id));
    }
}