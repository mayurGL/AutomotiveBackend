package com.global.automotivebackend.controller;

import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.CompanyHistorical;
import com.global.automotivebackend.service.CompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CompanyControllerTest {

    @Mock
    private CompanyService companyService;

    private CompanyController companyController;

    @BeforeEach
    void setUp() {
        companyController = new CompanyController(companyService);
    }

    @Test
    public void testGetAllCompanies_ReturnsListOfCompanies() {

        List<Company> expectedCompanies = Arrays.asList(
                new Company(1, "ABC Company", "Address", LocalDateTime.now(), LocalDateTime.now(), "John", "John"),

                new Company(2, "xyz Company", "Address2", LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur")
        );
        when(companyService.getAllCompanies()).thenReturn(expectedCompanies);


        List<Company> actualCompanies = companyController.getAllCompanies();


        assertEquals(expectedCompanies.size(), actualCompanies.size());
        assertEquals(expectedCompanies.get(0), actualCompanies.get(0));
        assertEquals(expectedCompanies.get(1), actualCompanies.get(1));
    }

    @Test
    public void testGetAllCompanies_ReturnsEmptyList_WhenNoCompanies() {

        List<Company> expectedCompanies = List.of();
        when(companyService.getAllCompanies()).thenReturn(expectedCompanies);


        List<Company> actualCompanies = companyController.getAllCompanies();


        Assertions.assertTrue(actualCompanies.isEmpty());
    }

    @Test
    public void testGetCompany_ReturnsCompany_WhenValidId() {

        int companyId = 1;
        Company expectedCompany = new Company(1, "Company X", "Address", LocalDateTime.now(), LocalDateTime.now(), "John", "John");
        when(companyService.getCompany(companyId)).thenReturn(expectedCompany);


        Company actualCompany = companyController.getCompany(companyId);


        Assertions.assertEquals(expectedCompany, actualCompany);
    }

    @Test
    public void testGetCompany_ReturnsNull_WhenInvalidId() {

        int companyId = 99;
        when(companyService.getCompany(companyId)).thenReturn(null);


        Company actualCompany = companyController.getCompany(companyId);


        Assertions.assertNull(actualCompany);
    }

    @Test
    public void testGetCompany_ReturnsCompanyHistoricalList() {

        List<CompanyHistorical> expectedHistoricalData = Arrays.asList(
                new CompanyHistorical(UUID.randomUUID(), 2, "Company A", "Address A",LocalDateTime.now(), LocalDateTime.now(), "Person A","Person B"),
                new CompanyHistorical(UUID.randomUUID(), 3, "Company Z", "Address z",LocalDateTime.now(), LocalDateTime.now(), "Person Z","Person Z")
        );
        when(companyService.getCompanyHistorical()).thenReturn(expectedHistoricalData);


        List<CompanyHistorical> actualHistoricalData = companyController.getCompany();


        Assertions.assertEquals(expectedHistoricalData.size(), actualHistoricalData.size());
        Assertions.assertEquals(expectedHistoricalData.get(0), actualHistoricalData.get(0));
        Assertions.assertEquals(expectedHistoricalData.get(1), actualHistoricalData.get(1));
    }

    @Test
    public void testGetCompany_ReturnsEmptyList_WhenNoHistoricalData() {

        List<CompanyHistorical> expectedHistoricalData = List.of();
        when(companyService.getCompanyHistorical()).thenReturn(expectedHistoricalData);


        List<CompanyHistorical> actualHistoricalData = companyController.getCompany();


        Assertions.assertTrue(actualHistoricalData.isEmpty());
    }





}
