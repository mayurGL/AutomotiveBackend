package com.global.automotivebackend.controller;

import com.global.automotivebackend.dto.CompanyDTO;
import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/all")
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PostMapping("/add")
    public CrudResponse addCompany(@RequestBody CompanyDTO companyData){
       return companyService.addCompany(companyData, Instant.now());
    }

    @PutMapping("/update")
    public CrudResponse updateCompany(@RequestBody CompanyDTO companyData){
        return companyService.updateCompany(companyData, Instant.now());
    }

    @DeleteMapping("/delete/{id}")
    public CrudResponse deleteCompany(@PathVariable String id){
        return companyService.deleteCompanyById(id);
    }


}
