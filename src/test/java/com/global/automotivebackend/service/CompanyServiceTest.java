package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.repository.CompanyHistoricalRepository;
import com.global.automotivebackend.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class CompanyServiceTest {


    @Mock
    CompanyRepository companyRepository;

    @Mock
    CompanyHistoricalRepository companyHistoricalRepository;

    @Autowired
    CompanyService companyService;

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
    public void testGetCompany_ExistingCompany() {

        Integer companyId = 5;
        Company expectedCompany =  new Company(5, "Infosys", "Pune", LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(expectedCompany));


        Company actualCompany = companyService.getCompany(companyId);


        assertEquals(expectedCompany, actualCompany);
        verify(companyRepository, times(1)).findById(companyId);
    }

    @Test
    public void testGetCompany_NonExistingCompany() {

        Integer companyId = 19;

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());


        assertThrows(NoSuchElementException.class, () -> companyService.getCompany(companyId));
        verify(companyRepository, times(1)).findById(companyId);
    }




    @Test
    void testAddCompany() {

        Company company =  new Company(7, "Meta", "California", null, null, "Mayur", "Mayur");
        GenericResponse testCase1 = new GenericResponse();
        testCase1.setMessage("Company with " + company.getCompanyId() + " is added");
        testCase1.setStatus(true);

        assertThat(companyService.addCompany(company, LocalDateTime.now())).isEqualTo(testCase1);


    }



    @Test
    public void testDeleteCompanyById_ExistingCompany() {

        Integer companyId = 1;
        Company company = new Company(1, "GlobalLogic", "Noida", null, null, "Mayur", "Mayur");

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(company));


        GenericResponse response = companyService.deleteCompanyById(companyId);


        assertTrue(response.isStatus());
        assertEquals("Company with " + companyId + " is deleted", response.getMessage());
        verify(companyRepository, times(1)).findById(companyId);
        verify(companyRepository, times(1)).deleteById(companyId);
    }

    @Test
    public void testDeleteCompanyById_NonExistingCompany() {

        Integer companyId = 19;

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());


        GenericResponse response = companyService.deleteCompanyById(companyId);


        assertFalse(response.isStatus());
        assertEquals("Company with " + companyId + " is not found", response.getMessage());
        verify(companyRepository, times(1)).findById(companyId);
        verify(companyRepository, never()).deleteById(any());
    }



    @Test
    public void testUpdateCompany_ExistingCompany() {

        int companyId = 4;
        LocalDateTime modifiedTime = LocalDateTime.now();
        Company existingCompany = new Company(4, "TCS", "Hyderabad", LocalDateTime.now(), LocalDateTime.now(), "Mayur", "Mayur");
        Company updatedCompany = new Company(companyId, "Tata Steel", "Mumbai", existingCompany.getCreatedTime(), modifiedTime, existingCompany.getCreatedBy(), "Rahul");
        GenericResponse expectedResponse = new GenericResponse("Company with " + companyId + " is updated", true);

        when(companyRepository.findById(companyId)).thenReturn(Optional.of(existingCompany));
        when(companyRepository.save(updatedCompany)).thenReturn(updatedCompany);


        GenericResponse response = companyService.updateCompany(updatedCompany, modifiedTime);


        assertEquals(expectedResponse, response);
        verify(companyRepository, times(1)).findById(companyId);
        verify(companyRepository, times(1)).save(updatedCompany);
    }

    @Test
    public void testUpdateCompany_NonExistingCompany() {

        Integer companyId = 19;
        LocalDateTime modifiedTime = LocalDateTime.now();
        Company updatedCompany = new Company(companyId, "Updated Company", "Updated Address", LocalDateTime.now(), modifiedTime, "createdBy", "modifiedBy");
        GenericResponse expectedResponse = new GenericResponse( "Company with " + companyId + " is not found", false);

        when(companyRepository.findById(companyId)).thenReturn(Optional.empty());


        GenericResponse response = companyService.updateCompany(updatedCompany, modifiedTime);


        assertEquals(expectedResponse, response);
        verify(companyRepository, times(1)).findById(companyId);
        verify(companyRepository, never()).save(any());
        verify(companyHistoricalRepository, never()).save(any());
    }
}