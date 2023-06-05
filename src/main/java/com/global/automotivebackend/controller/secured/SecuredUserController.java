package com.global.automotivebackend.controller.secured;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * secured controller class to handle user
 */
@RestController
@RequestMapping("/user")
public class SecuredUserController {

    /*
     * method to logout user
     */
    @GetMapping("/logout")
    @Operation(summary = "Logout a user", description = "This API endpoint logs out a user.")
    public ResponseEntity<GenericResponse> logout(HttpServletRequest request, HttpServletResponse response) {
        String token = CookieToJwtConverter.getTokenFromCookie(request);
        if (token == null || JwtUtil.validateToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Already logged out", false));
        }
        JwtUtil.invalidateToken(token);
        Cookie cookie = new Cookie("MyJWT", null);
        cookie.setMaxAge(0);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return ResponseEntity.status(HttpStatus.OK).body(new GenericResponse("Logged out successfully", true));
    }
}
