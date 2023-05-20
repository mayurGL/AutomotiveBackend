package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.CompanyHistorical;
import com.global.automotivebackend.repository.CompanyHistoricalRepository;
import com.global.automotivebackend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyHistoricalRepository companyHistoricalRepository;


    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyHistoricalRepository companyHistoricalRepository) {
        this.companyRepository = companyRepository;
        this.companyHistoricalRepository=companyHistoricalRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Integer id) {
        return companyRepository.findById(id).get();
    }

    public List<CompanyHistorical> getCompanyHistorical() {
        return companyHistoricalRepository.findAll();
    }

    public CrudResponse addCompany(Company company, LocalDateTime timestamp) {
        CrudResponse crudResponse = new CrudResponse();
        Company companyToBeSaved = new Company(company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress(), timestamp, timestamp, company.getCreatedBy(), company.getModifiedBy());
        CompanyHistorical companyHistorical = new CompanyHistorical(UUID.randomUUID(), company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress(), timestamp, timestamp, company.getCreatedBy(), company.getModifiedBy());
        companyRepository.save(companyToBeSaved);
        companyHistoricalRepository.save(companyHistorical);
        crudResponse.setMessage("Company with " + company.getCompanyId() + " is added");
        crudResponse.setStatus(true);
        return crudResponse;
    }

    public CrudResponse deleteCompanyById(Integer companyId) {
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

    public CrudResponse updateCompany(Company company, LocalDateTime modifiedTime) {
        CrudResponse crudResponse = new CrudResponse();
        Optional<Company> searchedCompany = companyRepository.findById(company.getCompanyId());
        if (searchedCompany.isPresent()) {
            Company companyToBeUpdated = new Company(company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress(), searchedCompany.get().getCreatedTime(), modifiedTime, searchedCompany.get().getCreatedBy(), company.getModifiedBy());
            CompanyHistorical companyHistorical = new CompanyHistorical(UUID.randomUUID(), company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress(), searchedCompany.get().getCreatedTime(), modifiedTime, searchedCompany.get().getCreatedBy(), company.getModifiedBy());
            companyRepository.save(companyToBeUpdated);
            companyHistoricalRepository.save(companyHistorical);
            crudResponse.setMessage("Company with " + company.getCompanyId() + " is updated");
            crudResponse.setStatus(true);
        } else {
            crudResponse.setMessage("Company with " + company.getCompanyId() + " is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }
}