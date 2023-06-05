package com.global.automotivebackend.controller.secured;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Device;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.service.DeviceService;
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
 * secured controller class to handle device
 */
@RestController
@RequestMapping("/device")
public class SecuredDeviceController {

    @Autowired
    private DeviceService deviceService;

    /*
     * method to add device
     */
    @PostMapping("/add")
    @Operation(summary = "Add a device", description = "This API endpoint adds a device, if device with same ID exists it throws exception.")
    public ResponseEntity<GenericResponse> addDevice(@Valid @RequestBody Device device, HttpServletRequest request) {
        String token = CookieToJwtConverter.getTokenFromCookie(request);
        if (token == null || JwtUtil.validateToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login", false));
        }
        User user = JwtUtil.validateToken(token);
        device.setCreatedBy(user.getUsername());
        device.setModifiedBy(user.getUsername());
        return ResponseEntity.ok(deviceService.addDevice(device, LocalDateTime.now()));
    }

    /*
     * method to update device
     */
    @PutMapping("/update")
    @Operation(summary = "Update a device", description = "This API endpoint updates a device, if device with the ID doesn't exist it throws exception.")
    public ResponseEntity<GenericResponse> updateDevice(@Valid @RequestBody Device device, HttpServletRequest request) {
        String token = CookieToJwtConverter.getTokenFromCookie(request);
        if (token == null || JwtUtil.validateToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login", false));
        }
        User user = JwtUtil.validateToken(token);
        device.setModifiedBy(user.getUsername());
        return ResponseEntity.ok(deviceService.updateDevice(device, LocalDateTime.now()));
    }

    /*
     * method to delete device
     */
    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a device", description = "This API endpoint deletes a device, if device with the ID doesn't exist it throws exception.")
    public ResponseEntity<GenericResponse> deleteDevice(@PathVariable Integer id, HttpServletRequest request) {
        String token = CookieToJwtConverter.getTokenFromCookie(request);
        if (token == null || JwtUtil.validateToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login", false));
        }
        return ResponseEntity.ok(deviceService.deleteDeviceById(id));
    }
}
