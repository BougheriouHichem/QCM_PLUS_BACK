package com.pfe.qcm_plus_back.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pfe.qcm_plus_back.entity.Admin;
import com.pfe.qcm_plus_back.entity.LoginRequest;
import com.pfe.qcm_plus_back.entity.Stagiaire;
import com.pfe.qcm_plus_back.service.CustomUserDetailsService;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userDetailsService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        
        if (isAuthenticated) {
            return ResponseEntity.ok("Authentication successful");
        } else {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }
   
    
    @PostMapping("/register/admin")
    public String registerAdmin(@RequestBody Admin admin) {
        userDetailsService.saveAdmin(admin);
        return "Admin registered successfully!";
    }

    @PostMapping("/register/stagiaire")
    public String registerStagiaire(@RequestBody Stagiaire stagiaire) {
        userDetailsService.saveStagiaire(stagiaire);
        return "Stagiaire registered successfully!";
    }
        
    }