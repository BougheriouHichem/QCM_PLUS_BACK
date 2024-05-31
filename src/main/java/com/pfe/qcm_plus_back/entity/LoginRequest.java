package com.pfe.qcm_plus_back.entity;

public class LoginRequest {
    private String email;
    private String password;

    // Constructeur par défaut
    public LoginRequest() {}

    // Getters et setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}