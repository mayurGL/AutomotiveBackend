package com.global.automotivebackend.service;

import com.global.automotivebackend.dto.CompanyDTO;
import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public CrudResponse addCompany(CompanyDTO company, Instant timeStatus) {
        CrudResponse crudResponse = new CrudResponse();
        Company companyToBeSaved = new Company(timeStatus, company.getCreatedBy(), company.getModifiedBy(), company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress());
        companyRepository.save(companyToBeSaved);
        crudResponse.setMessage("Company with " + company.getCompanyId() + " is added");
        crudResponse.setStatus(true);
        return crudResponse;
    }

    public CrudResponse deleteCompanyById(String companyId) {

        CrudResponse crudResponse = new CrudResponse();

        Optional<Company> company = companyRepository.findByCompanyId(companyId);
            if(company.isPresent())
            {   companyRepository.deleteByCompanyId(companyId);
                crudResponse.setMessage("Company with "+companyId+" is deleted");
                crudResponse.setStatus(true);
            }
            else {
                crudResponse.setMessage("Company with "+companyId+" is not found");
                crudResponse.setStatus(false);
            }

            return crudResponse;
    }

    public CrudResponse updateCompany(CompanyDTO company, Instant timestatus){

        CrudResponse crudResponse = new CrudResponse();
        Optional<Company> searchedCompany = companyRepository.findByCompanyId(company.getCompanyId());

        if (searchedCompany.isPresent()){
            Company companyToBeUpdated = new Company(timestatus, company.getCreatedBy(), company.getModifiedBy(), company.getCompanyId(), company.getCompanyName(), company.getCompanyAddress());
            companyRepository.save(companyToBeUpdated);
            crudResponse.setMessage("Company with "+company.getCompanyId()+" is updated");
            crudResponse.setStatus(true);
        }

        else {
            crudResponse.setMessage("Company with "+company.getCompanyId()+" is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }




}
