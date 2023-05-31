package com.global.automotivebackend.controller.secured;


import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.service.CompanyService;
import com.global.automotivebackend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SecuredCompanyControllerTest {

    @Mock
    private CompanyService companyService;

    private SecuredCompanyController securedCompanyController;

    @BeforeEach
    void setUp() {
        securedCompanyController = new SecuredCompanyController(companyService);
    }


//    @Test
//    public void testAddCompany_ReturnsUnauthorizedResponse_WhenTokenInvalid() {
//        // Arrange
//        Company company = new Company(1, "ABC Company", "Address", LocalDateTime.now(), LocalDateTime.now(), "John", "John");
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        when(CookieToJwtConverter.getTokenFromCookie(request)).thenReturn("invalidToken");
//        when(JwtUtil.validateToken("invalidToken")).thenReturn(null);
//
//        // Act
//        ResponseEntity<GenericResponse> responseEntity = securedCompanyController.addCompany(company, request);
//
//        // Assert
//        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertFalse(responseEntity.getBody().isStatus());
//        assertEquals("Unauthorized access please login", responseEntity.getBody().getMessage());
//
//
//    }
//
//    @Test
//    public void testAddCompany_ReturnsSuccessfulResponse_WhenTokenValid() {
//        // Arrange
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        when(CookieToJwtConverter.getTokenFromCookie(request)).thenReturn("validToken");
//        User user = new User("username","user@gmail.com" ,"password","myName");
//        when(JwtUtil.validateToken("validToken")).thenReturn(user);
//
//        Company company = new Company(3, "company A", "Address z", LocalDateTime.now(), LocalDateTime.now(), "Person M", "Person E");
//        LocalDateTime currentDateTime = LocalDateTime.now();
//
//        when(companyService.addCompany(company, currentDateTime)).thenReturn(new GenericResponse("Company with "+company.getCompanyId()+" is added", true));
//
//        // Act
//        ResponseEntity<GenericResponse> responseEntity = securedCompanyController.addCompany(company, request);
//
//        // Assert
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertTrue(responseEntity.getBody().isStatus());
//
//        verify(companyService).addCompany(company, currentDateTime);
//    }
}
