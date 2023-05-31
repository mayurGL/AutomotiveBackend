package com.global.automotivebackend.controller;

import com.global.automotivebackend.advice.AuthenticationFailedException;
import com.global.automotivebackend.advice.UserAlreadyExistsException;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.dto.LoginUserRequest;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController(userService);
    }


    @Test
    public void testRegister_ReturnsValidResponse() {

        User user = new User("mayur@gg","mayur@gmail.com","123@#","mayur");
        GenericResponse expectedResponse = new GenericResponse("User registered successfully",true);
        when(userService.register(user)).thenReturn(expectedResponse);


        GenericResponse actualResponse = userController.register(user);


        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    public void testRegister_InvalidUser_ReturnsErrorResponse() {

        User user = new User("","mayur@gmail.com","123@#","mayur"); // Invalid user with empty first name

        when(userService.register(user)).thenThrow(new UserAlreadyExistsException("User with this username already exists"));


       UserAlreadyExistsException exception = assertThrows(UserAlreadyExistsException.class,()-> userService.register(user));


        assertEquals("User with this username already exists", exception.getMessage());
    }


    @Test
    public void testLogin_ReturnsOkResponseAndCookie_WhenValidCredentials() {
        // Arrange
        LoginUserRequest loginUserRequest = new LoginUserRequest("username", "password");
        User user = new User("username", "example@gmail.com" ,"password", "name");
        when(userService.validateUser(loginUserRequest)).thenReturn(user);

        HttpServletResponse response = mock(HttpServletResponse.class);
        // Act
        ResponseEntity<GenericResponse> responseEntity = userController.login(loginUserRequest, response);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals("Login successful", responseEntity.getBody().getMessage());
        assertTrue(responseEntity.getBody().isStatus());

        verify(response).addCookie(any(Cookie.class));
    }

    @Test
    public void testLogin_ThrowsAuthenticationFailedException_WhenInvalidCredentials() {
        // Arrange
        LoginUserRequest loginUserRequest = new LoginUserRequest("username", "password");
        when(userService.validateUser(loginUserRequest)).thenReturn(null);

        HttpServletResponse response = mock(HttpServletResponse.class);


        // Act and Assert
        assertThrows(AuthenticationFailedException.class, () -> {
            userController.login(loginUserRequest, response);
        });
    }

}
