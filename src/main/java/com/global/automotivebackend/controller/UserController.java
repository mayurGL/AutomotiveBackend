package com.global.automotivebackend.controller;

import com.global.automotivebackend.advice.AuthenticationFailedException;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.dto.LoginUserRequest;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.service.UserService;
import com.global.automotivebackend.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * public controller class to handle user
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    private static final Logger logger = Logger.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /*
     * Method to register a user
     */
    @PostMapping("/register")
    @Operation(summary = "Register a user", description = "This API endpoint helps to register a new user.")
    public GenericResponse register(@Valid @RequestBody User user) {
        return userService.register(user);
    }

    /*
     * Method to get login a user
     */
    @PostMapping("/login")
    @Operation(summary = "Login a user", description = "This API endpoint help to login an already existing user.")
    public ResponseEntity<GenericResponse> login(@RequestBody LoginUserRequest loginUserRequest, HttpServletResponse response) {
        User userToBeLoggedIn = userService.validateUser(loginUserRequest);
        if (userToBeLoggedIn == null) {
            logger.error("Login Failed!!");
            throw new AuthenticationFailedException("ID and password not found!! TRY AGAIN!!");
        }
        String token = JwtUtil.generateToken(userToBeLoggedIn);
        Cookie cookie = new Cookie("MyJWT", token);
        cookie.setMaxAge(60 * 60 * 24 * 30);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        logger.info("User: " + loginUserRequest.getUsername() + " Login Successful!!");
        return ResponseEntity.ok(new GenericResponse("Login successful", true));
    }
}