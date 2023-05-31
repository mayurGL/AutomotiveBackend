package com.global.automotivebackend.service;

import com.global.automotivebackend.advice.UserAlreadyExistsException;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.dto.LoginUserRequest;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GenericResponse register(User user) {
        Optional<User> userToBeFound = userRepository.findById(user.getUsername());
        if (userToBeFound.isPresent()){
            throw new UserAlreadyExistsException("User with this username already exists");
        }

        userRepository.save(user);

        return new GenericResponse("User registered successfully",true);
    }

    @Override
    public User validateUser(LoginUserRequest loginUserRequest) {
       return userRepository.findByUsernameAndPassword(loginUserRequest.getUsername(), loginUserRequest.getPassword());
    }
}