package com.global.automotivebackend.service;

import com.global.automotivebackend.advice.IdAlreadyExistsException;
import com.global.automotivebackend.advice.IdNotFoundException;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.CompanyHistorical;
import com.global.automotivebackend.repository.CompanyHistoricalRepository;
import com.global.automotivebackend.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class CompanyServiceTest {


    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyHistoricalRepository companyHistoricalRepository;


    private CompanyService companyService;

    @BeforeEach
    void setUp() {
        companyService=new CompanyServiceImpl(companyRepository,companyHistoricalRepository);
    }

    @Test
    void testGetAllCompanies() {

        companyService.getAllCompanies();
        verify(companyRepository).findAll();
    }

    @Test
    void testGetCompanyHistorical(){

        companyService.getCompanyHistorical();
        verify(companyHistoricalRepository).findAll();
    }


    @Test
    public void testGetCompanyByIdExists() {

        Company company =  new Company(5, "Infosys", "Pune", LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");
        when(companyRepository.findById(5)).thenReturn(Optional.of(company));


        Company result = companyService.getCompany(5);


        assertNotNull(result);
        assertEquals(5, result.getCompanyId());
        assertEquals("Infosys", result.getCompanyName());


        verify(companyRepository).findById(5);
    }

    @Test
    public void testGetCompanyByIdNotExists() {

        when(companyRepository.findById(anyInt())).thenReturn(Optional.empty());


        assertThrows(IdNotFoundException.class, () -> companyService.getCompany(90));


        verify(companyRepository).findById(90);
    }


    @Test
    void testAddCompanySuccess() {

        Company company =  new Company(5, "Meta", "California", null, null, "Mayur", "Mayur");
        GenericResponse testCase1 = new GenericResponse();
        testCase1.setMessage("Company with " + company.getCompanyId() + " is added");
        testCase1.setStatus(true);

        when(companyRepository.findById(company.getCompanyId())).thenReturn(Optional.empty());

        GenericResponse companyAdded = companyService.addCompany(company,LocalDateTime.now());

        assertThat(companyAdded).isEqualTo(testCase1);


    }

    @Test
    void testAddCompanyWithExistingId() {

        Company company =  new Company(1, "XYZ Company", "Dallas", null, null, "Mayur", "Mayur");

        LocalDateTime timestamp = LocalDateTime.now();

        when(companyRepository.findById(company.getCompanyId())).thenReturn(Optional.of(company));


        IdAlreadyExistsException exception = assertThrows(IdAlreadyExistsException.class, () -> companyService.addCompany(company, timestamp));


        assertEquals("Company ID already exists", exception.getMessage());


    }


    @Test
    public void testDeleteCompanyByIdSuccess() {

        int companyId = 1;
        Company company = new Company(1, "GlobalLogic", "Noida", null, null, "Mayur", "Mayur");
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));


        GenericResponse response = companyService.deleteCompanyById(companyId);


        assertNotNull(response);
        assertTrue(response.isStatus());
        assertEquals("Company with " + companyId + " is deleted", response.getMessage());


        verify(companyRepository).findById(companyId);
        verify(companyRepository).deleteById(companyId);
    }

    @Test
    public void testDeleteCompanyByIdNotFound() {

        int companyId = 10;
        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());


        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> companyService.deleteCompanyById(companyId));


        assertEquals("Company ID doesn't exists", exception.getMessage());

    }


    @Test
    public void testUpdateCompanySuccess() {

        int companyId = 1;
        LocalDateTime modifiedTime = LocalDateTime.now();
        Company existingCompany = new Company(companyId, "ABC Company", "Address", LocalDateTime.now(), LocalDateTime.now(), "John", "John");
        Company updatedCompany = new Company(companyId, "Updated Company", "Updated Address", existingCompany.getCreatedTime(), modifiedTime, existingCompany.getCreatedBy(), "Person Y");
        when(companyRepository.findById(companyId)).thenReturn(Optional.of(existingCompany));
        when(companyRepository.save(updatedCompany)).thenReturn(updatedCompany);
        when(companyHistoricalRepository.save(any(CompanyHistorical.class))).thenReturn(null);


        GenericResponse response = companyService.updateCompany(updatedCompany, modifiedTime);


        assertNotNull(response);
        assertTrue(response.isStatus());
        assertEquals("Company with " + companyId + " is updated", response.getMessage());


        verify(companyRepository).findById(companyId);
        verify(companyRepository).save(updatedCompany);
        verify(companyHistoricalRepository).save(any(CompanyHistorical.class));
    }

    @Test
    public void testUpdateCompanyNotFound() {

        int companyId = 1;
        Company updatedCompany = new Company(1, "ABC company", "Hyderabad", LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");
        LocalDateTime modifiedTime = LocalDateTime.now();
        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());


        IdNotFoundException exception = assertThrows(IdNotFoundException.class, () -> companyService.updateCompany(updatedCompany, modifiedTime));


        assertEquals("Company ID doesn't exists", exception.getMessage());

    }








}