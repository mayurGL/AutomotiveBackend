package com.global.automotivebackend.service;


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

    public CrudResponse addCompany(Company company, String timeStatus) {
        CrudResponse crudResponse = new CrudResponse();
        Company companyToBeSaved = new Company(company.getCompany_id(), timeStatus,timeStatus, company.getCreatedBy(), company.getModifiedBy(), company.getCompanyName(), company.getCompanyAddress());
        companyRepository.save(companyToBeSaved);
        crudResponse.setMessage("Company with " + company.getCompany_id() + " is added");
        crudResponse.setStatus(true);
        return crudResponse;
    }

    public CrudResponse deleteCompanyById(String companyId) {

        CrudResponse crudResponse = new CrudResponse();

        Optional<Company> company = companyRepository.findById(companyId);
            if(company.isPresent())
            {
                System.out.println(company.get());
                companyRepository.deleteById(companyId);
                crudResponse.setMessage("Company with "+companyId+" is deleted");
                crudResponse.setStatus(true);
            }
            else {
                crudResponse.setMessage("Company with "+companyId+" is not found");
                crudResponse.setStatus(false);
            }

            return crudResponse;
    }

    public CrudResponse updateCompany(Company company, String modified_time){

        CrudResponse crudResponse = new CrudResponse();
        Optional<Company> searchedCompany = companyRepository.findById(company.getCompany_id());

        if (searchedCompany.isPresent()){

            Company companyToBeUpdated = new Company(company.getCompany_id(), searchedCompany.get().getCreated_time(),modified_time,searchedCompany.get().getCreatedBy(), company.getModifiedBy(), company.getCompanyName(), company.getCompanyAddress());

            companyRepository.save(companyToBeUpdated);
            crudResponse.setMessage("Company with "+company.getCompany_id()+" is updated");
            crudResponse.setStatus(true);
        }

        else {
            crudResponse.setMessage("Company with "+company.getCompany_id()+" is not found");
            crudResponse.setStatus(false);
        }
        return crudResponse;
    }




}
