package com.PFE.QCM_PLUS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.PFE.QCM_PLUS.entity.LoginForm;
import com.PFE.QCM_PLUS.service.CustomUserDetailsService;


@RestController
@CrossOrigin
public class AuthController {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginForm loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        if (userDetailsService.authenticate(email, password)) {
            return ResponseEntity.ok("Authentication successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
}


