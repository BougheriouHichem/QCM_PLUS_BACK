package com.pfe.qcm_plus_back.service;


import com.pfe.qcm_plus_back.entity.Admin;
import com.pfe.qcm_plus_back.entity.Stagiaire;
import com.pfe.qcm_plus_back.repository.AdminRepository;
import com.pfe.qcm_plus_back.repository.StagiaireRepository;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StagiaireRepository stagiaireRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

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
        // Déconnexion de l'utilisateur s'il est déjà connecté
        logout(request);

        // Chargement des détails de l'utilisateur
        UserDetails userDetails = loadUserByUsername(email);

        // Validation du mot de passe
        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            // Démarrer une nouvelle session
            HttpSession session = request.getSession(true);
            session.setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
            return true;
        }

        return false;
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

    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // Invalider la session pour la déconnexion
        }
        SecurityContextHolder.clearContext(); // Effacer le contexte de sécurité
    }
}
