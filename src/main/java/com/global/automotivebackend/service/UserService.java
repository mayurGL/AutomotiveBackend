package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public CrudResponse register(User user);
}
