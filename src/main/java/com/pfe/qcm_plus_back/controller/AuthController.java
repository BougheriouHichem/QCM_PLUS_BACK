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

    @Autowired
    private jakarta.servlet.http.HttpServletRequest request;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = userDetailsService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        
        if (isAuthenticated) {
            return ResponseEntity.ok("Authentication successful");
        } else {
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        userDetailsService.logout(request);
        return ResponseEntity.ok("Logout successful");
    }
    
    @PostMapping("/register/admin")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        try {
            userDetailsService.saveAdmin(admin);
            return ResponseEntity.ok("Admin registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/register/stagiaire")
    public ResponseEntity<?> registerStagiaire(@RequestBody Stagiaire stagiaire) {
        try {
            userDetailsService.saveStagiaire(stagiaire);
            return ResponseEntity.ok("Stagiaire registered successfully!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
