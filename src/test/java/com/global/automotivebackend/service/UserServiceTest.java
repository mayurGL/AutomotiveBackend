package com.global.automotivebackend.service;

import com.global.automotivebackend.advice.UserAlreadyExistsException;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.dto.LoginUserRequest;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userRepository);
    }


    @Test
    public void testRegisterUserSuccess() {

        User user = new User("john123", "john@gmail.com","xyz123","John Cena");
        when(userRepository.findById(user.getUsername())).thenReturn(Optional.empty());


        GenericResponse response = userService.register(user);


        assertNotNull(response);
        assertTrue(response.isStatus());
        assertEquals("User registered successfully", response.getMessage());


        verify(userRepository).findById(user.getUsername());
        verify(userRepository).save(user);
    }

    @Test
    public void testRegisterUserAlreadyExists() {

        User existingUser = new User("carl2009", "carl@outlook.com","3#23y","Carl");
        when(userRepository.findById(existingUser.getUsername())).thenReturn(Optional.of(existingUser));


        UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class, () -> userService.register(existingUser));


        assertEquals("User with username: "+existingUser.getUsername()+" already exists", exception.getMessage());


        verify(userRepository).findById(existingUser.getUsername());

    }



    @Test
    public void testValidateUserSuccess() {

        String username = "john123";
        String password = "password";
        User expectedUser = new User(username, "john@outlook.com",password,"john ");
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(expectedUser);


        LoginUserRequest loginUserRequest = new LoginUserRequest(username, password);


        User actualUser = userService.validateUser(loginUserRequest);


        assertNotNull(actualUser);
        assertEquals(expectedUser, actualUser);


        verify(userRepository).findByUsernameAndPassword(username, password);
    }

    @Test
    public void testValidateUserNotFound() {

        String username = "john123";
        String password = "password";
        when(userRepository.findByUsernameAndPassword(username, password)).thenReturn(null);


        LoginUserRequest loginUserRequest = new LoginUserRequest(username, password);


        User actualUser = userService.validateUser(loginUserRequest);


        assertNull(actualUser);


        verify(userRepository).findByUsernameAndPassword(username, password);
    }




}
