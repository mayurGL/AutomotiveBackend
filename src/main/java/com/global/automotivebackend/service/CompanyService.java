package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompanyService {
    public List<Company> getAllCompanies();

    public CrudResponse addCompany(Company company, String timeStatus);

    public CrudResponse deleteCompanyById(String companyId);

    public CrudResponse updateCompany(Company company, String modified_time);
}
