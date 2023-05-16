package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.CompanyHistorical;
import com.global.automotivebackend.repository.CompanyHistoricalRepository;
import com.global.automotivebackend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyHistoricalRepository companyHistoricalRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public CrudResponse addCompany(Company company, String timeStatus) {
        CrudResponse crudResponse = new CrudResponse();
        Company companyToBeSaved = new Company(company.getCompany_id(), company.getCompanyName(), company.getCompanyAddress(), timeStatus, timeStatus, company.getCreatedBy(), company.getModifiedBy());
        CompanyHistorical companyHistorical = new CompanyHistorical(UUID.randomUUID(), company.getCompany_id(), company.getCompanyName(), company.getCompanyAddress(), timeStatus, timeStatus, company.getCreatedBy(), company.getModifiedBy());
        companyRepository.save(companyToBeSaved);
        companyHistoricalRepository.save(companyHistorical);
        crudResponse.setMessage("Company with " + company.getCompany_id() + " is added");
        crudResponse.setStatus(true);
        return crudResponse;
    }

    public CrudResponse deleteCompanyById(String companyId) {
        CrudResponse crudResponse = new CrudResponse();
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            System.out.println(company.get());
            companyRepository.deleteById(companyId);
            crudResponse.setMessage("Company with " + companyId + " is deleted");
            crudResponse.setStatus(true);
        } else {
            crudResponse.setMessage("Company with " + companyId + " is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }

    public CrudResponse updateCompany(Company company, String modified_time) {
        CrudResponse crudResponse = new CrudResponse();
        Optional<Company> searchedCompany = companyRepository.findById(company.getCompany_id());

        if (searchedCompany.isPresent()) {
            Company companyToBeUpdated = new Company(company.getCompany_id(), searchedCompany.get().getCreated_time(), modified_time, searchedCompany.get().getCreatedBy(), company.getModifiedBy(), company.getCompanyName(), company.getCompanyAddress());
            CompanyHistorical companyHistorical = new CompanyHistorical(UUID.randomUUID(), company.getCompany_id(), searchedCompany.get().getCreated_time(), modified_time, searchedCompany.get().getCreatedBy(), company.getModifiedBy(), company.getCompanyName(), company.getCompanyAddress());
            companyRepository.save(companyToBeUpdated);
            companyHistoricalRepository.save(companyHistorical);
            crudResponse.setMessage("Company with " + company.getCompany_id() + " is updated");
            crudResponse.setStatus(true);
        } else {
            crudResponse.setMessage("Company with " + company.getCompany_id() + " is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }
}