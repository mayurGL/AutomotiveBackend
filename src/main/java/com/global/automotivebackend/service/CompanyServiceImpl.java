package com.global.automotivebackend.service;

import com.global.automotivebackend.advice.IdAlreadyExistsException;
import com.global.automotivebackend.advice.IdNotFoundException;
import com.global.automotivebackend.dto.GenericResponse;
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
        this.companyHistoricalRepository = companyHistoricalRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompany(Integer id) {
        Optional<Company> companyToBeFound = companyRepository.findById(id);
        if (companyToBeFound.isEmpty()){
            throw new IdNotFoundException("Company ID does not exists");
        } else {
            return companyToBeFound.get();
        }
    }

    public List<CompanyHistorical> getCompanyHistorical() {
        return companyHistoricalRepository.findAll();
    }

    public GenericResponse addCompany(Company company, LocalDateTime timestamp) {
        GenericResponse crudResponse = new GenericResponse();
        Company companyToBeSaved = new Company(company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress(), timestamp, timestamp, company.getCreatedBy(), company.getModifiedBy());
        CompanyHistorical companyHistorical = new CompanyHistorical(UUID.randomUUID(), company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress(), timestamp, timestamp, company.getCreatedBy(), company.getModifiedBy());
        if (companyRepository.findById(companyToBeSaved.getCompanyId()).isPresent()){
            throw new IdAlreadyExistsException("Company ID already exists");
        } else {
            companyRepository.save(companyToBeSaved);
            companyHistoricalRepository.save(companyHistorical);
            crudResponse.setMessage("Company with " + company.getCompanyId() + " is added");
            crudResponse.setStatus(true);
            return crudResponse;
        }
    }

    public GenericResponse deleteCompanyById(Integer companyId) {
        GenericResponse crudResponse = new GenericResponse();
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            companyRepository.deleteById(companyId);
            crudResponse.setMessage("Company with " + companyId + " is deleted");
            crudResponse.setStatus(true);
        } else {
            throw new IdNotFoundException("Company ID doesn't exists");
        }
        return crudResponse;
    }

    public GenericResponse updateCompany(Company company, LocalDateTime modifiedTime) {
        GenericResponse crudResponse = new GenericResponse();
        Optional<Company> searchedCompany = companyRepository.findById(company.getCompanyId());
        if (searchedCompany.isPresent()) {
            Company companyToBeUpdated = new Company(company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress(), searchedCompany.get().getCreatedTime(), modifiedTime, searchedCompany.get().getCreatedBy(), company.getModifiedBy());
            CompanyHistorical companyHistorical = new CompanyHistorical(UUID.randomUUID(), company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress(), searchedCompany.get().getCreatedTime(), modifiedTime, searchedCompany.get().getCreatedBy(), company.getModifiedBy());
            companyRepository.save(companyToBeUpdated);
            companyHistoricalRepository.save(companyHistorical);
            crudResponse.setMessage("Company with " + company.getCompanyId() + " is updated");
            crudResponse.setStatus(true);
        } else {
            throw new IdNotFoundException("Company ID doesn't exists");
        }
        return crudResponse;
    }
}