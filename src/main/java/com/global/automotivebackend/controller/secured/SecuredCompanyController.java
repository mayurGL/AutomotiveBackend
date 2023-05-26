package com.global.automotivebackend.controller.secured;

import com.global.automotivebackend.dto.GenericResponse;
import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.User;
import com.global.automotivebackend.service.CompanyService;
import com.global.automotivebackend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/company")
public class SecuredCompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/add")
    public ResponseEntity<GenericResponse> addCompany(@Valid @RequestBody Company company, HttpServletRequest request) {

        String token = CookieToJwtConverter.getTokenFromCookie(request);

        if(token == null || JwtUtil.validateToken(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login",false));
        }

        User user = JwtUtil.validateToken(token);

        company.setCreatedBy(user.getUsername());
        company.setModifiedBy(user.getUsername());


        return ResponseEntity.ok(companyService.addCompany(company, LocalDateTime.now()));
    }

    @PutMapping("/update")
    public ResponseEntity<GenericResponse> updateCompany(@Valid @RequestBody Company company, HttpServletRequest request) {

        String token = CookieToJwtConverter.getTokenFromCookie(request);

        if(token == null || JwtUtil.validateToken(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login",false));
        }

        User user = JwtUtil.validateToken(token);

        company.setModifiedBy(user.getUsername());

        return ResponseEntity.ok(companyService.updateCompany(company, LocalDateTime.now()));
    }

    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<GenericResponse> deleteCompany(@PathVariable int companyId, HttpServletRequest request) {

        String token = CookieToJwtConverter.getTokenFromCookie(request);

        if(token == null || JwtUtil.validateToken(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new GenericResponse("Unauthorized access please login",false));
        }

        return ResponseEntity.ok(companyService.deleteCompanyById(companyId));
    }
}
