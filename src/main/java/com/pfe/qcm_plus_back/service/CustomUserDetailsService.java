package com.pfe.qcm_plus_back.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pfe.qcm_plus_back.entity.Admin;
import com.pfe.qcm_plus_back.entity.Stagiaire;
import com.pfe.qcm_plus_back.repository.AdminRepository;
import com.pfe.qcm_plus_back.repository.StagiaireRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StagiaireRepository stagiaireRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            Admin admin = adminRepository.findByEmail(email);
            if (admin != null) {
                return new User(admin.getEmail(), admin.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
            }
            Stagiaire stagiaire = stagiaireRepository.findByEmail(email);
            if (stagiaire != null) {
                return new User(stagiaire.getEmail(), stagiaire.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_STAGIAIRE")));
            }
        } catch (IncorrectResultSizeDataAccessException e) {
            throw new UsernameNotFoundException("Multiple users found with email: " + email);
        }

        throw new UsernameNotFoundException("User not found with email: " + email);
    }

        
        public boolean authenticate(String email, String password) {
            UserDetails userDetails = loadUserByUsername(email);
            return passwordEncoder.matches(password, userDetails.getPassword());
        }
        
        public void saveAdmin(Admin admin) {
            if (adminRepository.findByEmail(admin.getEmail()) != null) {
                throw new IllegalArgumentException("Email already in use");
            }
            admin.setPassword(passwordEncoder.encode(admin.getPassword()));
            adminRepository.save(admin);
        }

        public void saveStagiaire(Stagiaire stagiaire) {
            if (stagiaireRepository.findByEmail(stagiaire.getEmail()) != null) {
                throw new IllegalArgumentException("Email already in use");
            }
            stagiaire.setPassword(passwordEncoder.encode(stagiaire.getPassword()));
            stagiaireRepository.save(stagiaire);
        }
    }