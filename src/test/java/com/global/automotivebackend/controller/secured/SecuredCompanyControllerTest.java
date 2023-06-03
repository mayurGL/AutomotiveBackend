package com.global.automotivebackend.controller.secured;


import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.service.CompanyService;
import com.global.automotivebackend.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class SecuredCompanyControllerTest {

//    @Mock
//    private CompanyService companyService;
//
//    private SecuredCompanyController securedCompanyController;
//
//    @BeforeEach
//    void setUp() {
//        securedCompanyController = new SecuredCompanyController(companyService);
//    }

//    @Test
//    public void testSuccessAddCompany(){
//
//        Company company = new Company();
//        company.setCompanyId(1);
//        company.setCompanyName("Test Company");
//        HttpServletRequest request = mock(HttpServletRequest.class);
//
//        Mockito.when(request.getHeader("Cookie")).thenReturn("MyJWT=exampleToken");
//        Mockito.when(JwtUtil.validateToken("exampleToken")).thenReturn(new User("username", "example@gmail.com" ,"password", "name"));
//
//        Mockito.when(companyService.addCompany(company, LocalDateTime.now()))
//                .thenReturn(new GenericResponse("Company with "+company.getCompanyId()+" is added", true));
//
//        ResponseEntity<GenericResponse> response = securedCompanyController.addCompany(company,request);
//
//        Assertions.assertEquals(true,response.getBody().isStatus());
//    }







//    @Test
//    public void testAddCompany_ReturnsUnauthorizedResponse_WhenTokenInvalid() {
//        // Arrange
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        when(CookieToJwtConverter.getTokenFromCookie(request)).thenReturn(null);
//
//        // Act
//        ResponseEntity<GenericResponse> responseEntity = securedCompanyController.addCompany(new Company(), request);
//
//        // Assert
//        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertFalse(responseEntity.getBody().isStatus());
//        assertEquals("Unauthorized access please login", responseEntity.getBody().getMessage());
//
//        verify(companyService, never()).addCompany(any(), any());
//    }
//
//    @Test
//    public void testAddCompany_ReturnsUnauthorizedResponse_WhenTokenExpired() {
//        // Arrange
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        String expiredToken = "expiredToken";
//
//
//        // Act
//        ResponseEntity<GenericResponse> responseEntity = securedCompanyController.addCompany(new Company(), request);
//
//        // Assert
//        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertFalse(responseEntity.getBody().isStatus());
//        assertEquals("Unauthorized access please login", responseEntity.getBody().getMessage());
//
//        verify(companyService, never()).addCompany(any(), any());
//    }

//    @Test
//    public void testAddCompany_ValidToken() {
//        // Arrange
//        User user = new User("username", "example@gmail.com" ,"password", "name");
//
//
//        Company company = new Company();
//        company.setCompanyId(2);
//        company.setCreatedBy(user.getUsername());
//        company.setCreatedBy(user.getUsername());
//
//
//        String validToken="validToken";
//
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        MockedStatic<CookieToJwtConverter> converterMockedStatic = mockStatic(CookieToJwtConverter.class);
//        MockedStatic<JwtUtil> jwtUtilMockedStatic  = mockStatic(JwtUtil.class);
//
//        converterMockedStatic.when(()-> CookieToJwtConverter.getTokenFromCookie(request)).thenReturn(validToken);
//        jwtUtilMockedStatic.when(()->JwtUtil.validateToken(validToken)).thenReturn(user).thenCallRealMethod();
//
//
//        when(companyService.addCompany(company,LocalDateTime.now())).thenReturn(new GenericResponse("Company with " + company.getCompanyId() + " is added",true));
//        when(securedCompanyController.addCompany(company,request)).thenReturn(ResponseEntity.ok(new GenericResponse("Company with " + company.getCompanyId() + " is added",true)));
//
//
//        // Act
//        ResponseEntity<GenericResponse> response = securedCompanyController.addCompany(company, request);
//
//        // Assert
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//        Assertions.assertTrue(response.getBody().isStatus());
//
//        converterMockedStatic.close();
//        jwtUtilMockedStatic.close();
//    }

//    @Test
//    public void testAddCompany_InvalidToken() {
//        // Arrange
//        Company company = new Company();
//        company.setCompanyName("Test Company");
//
//        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
//        PowerMockito.mockStatic(CookieToJwtConverter.class);
//        PowerMockito.when(CookieToJwtConverter.getTokenFromCookie(request)).thenReturn(null);
//
//        SecuredCompanyController companyController = new SecuredCompanyController(companyService);
//
//        // Act
//        ResponseEntity<GenericResponse> response = companyController.addCompany(company, request);
//
//        // Assert
//        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
//        Assertions.assertFalse(response.getBody().isStatus());
//        Assertions.assertEquals("Unauthorized access please login", response.getBody().getMessage());
//    }


}
