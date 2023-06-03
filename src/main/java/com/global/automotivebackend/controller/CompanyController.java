package com.global.automotivebackend.controller;

import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.CompanyHistorical;
import com.global.automotivebackend.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/all")
    @Operation(summary = "Get all companies", description = "This API endpoint gets all the company records available , else it will return an empty list if no companies are available")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "Get a company by an ID", description = "This API endpoint gets a comapny based on a unique ID available, else it will return a message of ID not found")
    public Company getCompany(@PathVariable int id) {
        return companyService.getCompany(id);
    }

    @GetMapping("/historical")
    @Operation(summary = "Get all historical records of companies", description = "This API endpoint gets all historical events occured in the company table and returns all the records")
    public List<CompanyHistorical> getCompany() {
        return companyService.getCompanyHistorical();
    }
}