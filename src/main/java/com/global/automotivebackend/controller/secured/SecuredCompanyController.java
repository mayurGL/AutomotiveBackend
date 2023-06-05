package com.global.automotivebackend.controller.secured;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.service.CompanyService;
import com.global.automotivebackend.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/*
 * secured controller class to handle company
 */
@RestController
@RequestMapping("/company")
public class SecuredCompanyController {

    @Autowired
    private CompanyService companyService;

    public SecuredCompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    /*
     * method to add company
     */
    @PostMapping("/add")
    @Operation(summary = "Add a company", description = "This API endpoint adds a company, if company with same ID exists it throws exception.")
    public ResponseEntity<GenericResponse> addCompany(@Valid @RequestBody Company company, HttpServletRequest request) {
        String token = CookieToJwtConverter.getTokenFromCookie(request);
        if (token == null || JwtUtil.validateToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login", false));
        }
        User user = JwtUtil.validateToken(token);
        company.setCreatedBy(user.getUsername());
        company.setModifiedBy(user.getUsername());
        return ResponseEntity.ok(companyService.addCompany(company, LocalDateTime.now()));
    }

    /*
     * method to update company
     */
    @PutMapping("/update")
    @Operation(summary = "Update a company", description = "This API endpoint updates a company, if company with the ID doesn't exist it throws exception.")
    public ResponseEntity<GenericResponse> updateCompany(@Valid @RequestBody Company company, HttpServletRequest request) {
        String token = CookieToJwtConverter.getTokenFromCookie(request);
        if (token == null || JwtUtil.validateToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login", false));
        }
        User user = JwtUtil.validateToken(token);
        company.setModifiedBy(user.getUsername());
        return ResponseEntity.ok(companyService.updateCompany(company, LocalDateTime.now()));
    }

    /*
     * method to delete company
     */
    @DeleteMapping("/delete/{companyId}")
    @Operation(summary = "Delete a company", description = "This API endpoint deletes a company, if company with the ID doesn't exist it throws exception.")
    public ResponseEntity<GenericResponse> deleteCompany(@PathVariable int companyId, HttpServletRequest request) {
        String token = CookieToJwtConverter.getTokenFromCookie(request);
        if (token == null || JwtUtil.validateToken(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login", false));
        }
        return ResponseEntity.ok(companyService.deleteCompanyById(companyId));
    }
}
