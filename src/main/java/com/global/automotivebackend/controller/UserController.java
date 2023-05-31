package com.global.automotivebackend.controller;


import com.global.automotivebackend.advice.AuthenticationFailedException;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.dto.LoginUserRequest;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.service.UserService;
import com.global.automotivebackend.util.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public GenericResponse register(@Valid @RequestBody User user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<GenericResponse> login(@RequestBody LoginUserRequest loginUserRequest, HttpServletResponse response){

        User userToBeLoggedIn = userService.validateUser(loginUserRequest);
        if(userToBeLoggedIn==null){
            throw new AuthenticationFailedException("ID and password not found!! TRY AGAIN!!");
        }

        String token= JwtUtil.generateToken(userToBeLoggedIn);
        Cookie cookie = new Cookie("MyJWT", token);
        cookie.setMaxAge(60*60*24*30);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(new GenericResponse("Login successful",true));
    }
}
