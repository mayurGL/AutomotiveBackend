package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.CompanyHistorical;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface CompanyService {
    public List<Company> getAllCompanies();

    public Company getCompany(Integer id);

    public List<CompanyHistorical> getCompanyHistorical();

    public CrudResponse addCompany(Company company, LocalDateTime timestamp);

    public CrudResponse deleteCompanyById(Integer companyId);

    public CrudResponse updateCompany(Company company, LocalDateTime modifiedTime);
}
