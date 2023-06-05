package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.CompanyHistorical;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/*
 * Service interface for company controller to handle
 * company entity and company historical entity
 */
@Service
public interface CompanyService {
    List<Company> getAllCompanies();

    Company getCompany(Integer id);

    List<CompanyHistorical> getCompanyHistorical();

    GenericResponse addCompany(Company company, LocalDateTime timestamp);

    GenericResponse deleteCompanyById(Integer companyId);

    GenericResponse updateCompany(Company company, LocalDateTime modifiedTime);
}
