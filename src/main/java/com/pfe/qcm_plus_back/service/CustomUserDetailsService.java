package com.pfe.qcm_plus_back.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
            // Rechercher l'utilisateur dans la base de données en fonction de son email
            Admin admin = adminRepository.findByEmail(email);
            Stagiaire stagiaire = stagiaireRepository.findByEmail(email);

            if (admin != null) {
                return new User(admin.getEmail(), admin.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
            } else if (stagiaire != null) {
                return new User(stagiaire.getEmail(), stagiaire.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_STAGIAIRE")));
            } else {
                throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email);
            }
        }
        
        public boolean authenticate(String email, String password) {
            UserDetails userDetails = loadUserByUsername(email);
            return passwordEncoder.matches(password, userDetails.getPassword());
        }
    }