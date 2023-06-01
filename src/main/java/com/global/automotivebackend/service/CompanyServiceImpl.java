package com.global.automotivebackend.service;

import com.global.automotivebackend.advice.IdAlreadyExistsException;
import com.global.automotivebackend.advice.IdNotFoundException;
import com.global.automotivebackend.controller.CompanyController;
import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.CompanyHistorical;
import com.global.automotivebackend.repository.CompanyHistoricalRepository;
import com.global.automotivebackend.repository.CompanyRepository;
import org.apache.log4j.Logger;
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

    private static final Logger logger = Logger.getLogger(CompanyServiceImpl.class);

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyHistoricalRepository companyHistoricalRepository) {
        this.companyRepository = companyRepository;
        this.companyHistoricalRepository = companyHistoricalRepository;
    }

    public List<Company> getAllCompanies() {
        logger.info("@GET - Print all companies");
        return companyRepository.findAll();
    }

    public Company getCompany(Integer id) {
        Optional<Company> companyToBeFound = companyRepository.findById(id);
        if (companyToBeFound.isEmpty()){
            logger.error("@GET - Company with ID: " + id + " doesn't exist");
            throw new IdNotFoundException("Company with ID: " + id + " doesn't exist");
        } else {
            logger.info("@GET - Print company with ID: " + id);
            return companyToBeFound.get();
        }
    }

    public List<CompanyHistorical> getCompanyHistorical() {
        logger.info("@GET - Print historical data of company");
        return companyHistoricalRepository.findAll();
    }

    public GenericResponse addCompany(Company company, LocalDateTime timestamp) {
        GenericResponse crudResponse = new GenericResponse();
        Company companyToBeSaved = new Company(company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress(), timestamp, timestamp, company.getCreatedBy(), company.getModifiedBy());
        CompanyHistorical companyHistorical = new CompanyHistorical(UUID.randomUUID(), company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress(), timestamp, timestamp, company.getCreatedBy(), company.getModifiedBy());
        if (companyRepository.findById(companyToBeSaved.getCompanyId()).isPresent()){
            logger.error("@POST - Company with ID: " + company.getCompanyId() + " already exists");
            throw new IdAlreadyExistsException("Company with ID: " + company.getCompanyId() + " already exists");
        } else {
            companyRepository.save(companyToBeSaved);
            companyHistoricalRepository.save(companyHistorical);
            crudResponse.setMessage("Company with ID: " + company.getCompanyId() + " added");
            logger.info("@POST - Company with ID: " + company.getCompanyId() + " added");
            crudResponse.setStatus(true);
            return crudResponse;
        }
    }

    public GenericResponse deleteCompanyById(Integer companyId) {
        GenericResponse crudResponse = new GenericResponse();
        Optional<Company> company = companyRepository.findById(companyId);
        if (company.isPresent()) {
            companyRepository.deleteById(companyId);
            crudResponse.setMessage("Company with ID: " + companyId + " deleted");
            logger.info("@DELETE - Company with ID: " + companyId + " deleted");
            crudResponse.setStatus(true);
        } else {
            logger.error("@DELETE - Company with ID: " + companyId + " doesn't exist");
            throw new IdNotFoundException("Company with ID: " + companyId + " doesn't exist");
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
            crudResponse.setMessage("Company with ID: " + company.getCompanyId() + "  updated");
            logger.info("@PUT - Company with ID: " + company.getCompanyId() + " updated");
            crudResponse.setStatus(true);
        } else {
            logger.error("@PUT - Company with ID: " + company.getCompanyId() + " doesn't exist");
            throw new IdNotFoundException("Company with ID: " + company.getCompanyId() + " doesn't exist");
        }
        return crudResponse;
    }
}