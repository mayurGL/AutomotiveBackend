package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.dto.LoginUserRequest;
import com.global.automotivebackend.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public GenericResponse register(User user);
    public User validateUser(LoginUserRequest loginUserRequest);
}
