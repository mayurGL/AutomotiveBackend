package com.global.automotivebackend.service;

import com.global.automotivebackend.advice.UserAlreadyExistsException;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.dto.LoginUserRequest;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public GenericResponse register(User user) {
        Optional<User> userToBeFound = userRepository.findById(user.getUsername());
        if (userToBeFound.isPresent()){
            logger.error("User with username: " + user.getUsername() + " doesn't exist");
            throw new UserAlreadyExistsException("User with username: " + user.getUsername() + " already exists");
        } else {
            userRepository.save(user);
            logger.info("@POST - User with username: " + user.getUsername() + " registered");
            return new GenericResponse("User registered successfully", true);
        }
    }

    @Override
    public User validateUser(LoginUserRequest loginUserRequest) {
       return userRepository.findByUsernameAndPassword(loginUserRequest.getUsername(), loginUserRequest.getPassword());
    }
}