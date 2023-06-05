package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.dto.LoginUserRequest;
import com.global.automotivebackend.model.User;
import org.springframework.stereotype.Service;

/*
 * Service interface for user controller to handle user entity
 */
@Service
public interface UserService {
    GenericResponse register(User user);
    User validateUser(LoginUserRequest loginUserRequest);
}
