package com.global.automotivebackend.controller;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.CompanyHistorical;
import com.global.automotivebackend.service.CompanyService;
import jakarta.validation.Valid;
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

    @GetMapping("/get/{id}")
    public Company getCompany(@PathVariable int id) {
        return companyService.getCompany(id);
    }

    @GetMapping("/historical")
    public List<CompanyHistorical> getCompany() {
        return companyService.getCompanyHistorical();
    }


}