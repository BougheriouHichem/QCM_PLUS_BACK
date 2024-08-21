package com.pfe.qcm_plus_back.service;

import com.pfe.qcm_plus_back.entity.Stagiaire;
import com.pfe.qcm_plus_back.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StagiaireService {

    @Autowired
    private StagiaireRepository stagiaireRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public Stagiaire addStagiaire(Stagiaire stagiaire){
        stagiaire.setPassword(passwordEncoder.encode(stagiaire.getPassword()));
        return stagiaireRepository.save(stagiaire);
    }

    public List<Stagiaire> getAllStagiaires () {
        return stagiaireRepository.findAll();
    }

    public Stagiaire updateStagiaire(Long id, Stagiaire stagiaireDetails) {
        Stagiaire stagiaire = stagiaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stagiaire not found on :: " + id));

        Optional.ofNullable(stagiaireDetails.getFirstName()).ifPresent(stagiaire::setFirstName);
        Optional.ofNullable(stagiaireDetails.getLastName()).ifPresent(stagiaire::setLastName);
        Optional.ofNullable(stagiaireDetails.getEmail()).ifPresent(stagiaire::setEmail);
        Optional.ofNullable(stagiaireDetails.isActive()).ifPresent(stagiaire::setActive);

        // Encodage du mot de passe seulement s'il est fourni et modifié
        if (stagiaireDetails.getPassword() != null && !stagiaireDetails.getPassword().isEmpty()) {
            stagiaire.setPassword(passwordEncoder.encode(stagiaireDetails.getPassword()));
        }

        return stagiaireRepository.save(stagiaire);
    }

    public Optional<Stagiaire> getStagiaireById(Long id) {
        return stagiaireRepository.findById(id);
    }

    public void deactivateStagiaire(Long id) {
        Stagiaire stagiaire = stagiaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stagiaire not found on :: " + id));
        stagiaire.setActive(false);
        stagiaireRepository.save(stagiaire);
    }

}
