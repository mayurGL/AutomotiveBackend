package com.global.automotivebackend.controller;

import com.global.automotivebackend.dto.CrudResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/all")
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @PostMapping("/add")
    public CrudResponse addCompany(@RequestBody Company company) {
        return companyService.addCompany(company, LocalDateTime.now());
    }

    @PutMapping("/update")
    public CrudResponse updateCompany(@RequestBody Company company) {
        return companyService.updateCompany(company, LocalDateTime.now());
    }

    @DeleteMapping("/delete/{companyId}")
    public CrudResponse deleteCompany(@PathVariable int companyId) {
        return companyService.deleteCompanyById(companyId);
    }
}