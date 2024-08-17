package com.pfe.qcm_plus_back.service;

import com.pfe.qcm_plus_back.entity.Stagiaire;
import com.pfe.qcm_plus_back.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StagiaireService {

    @Autowired
    private StagiaireRepository stagiaireRepository;


    public Stagiaire addStagiaire(Stagiaire stagiaire){
        return stagiaireRepository.save(stagiaire);
    }

    public List<Stagiaire> getAllStagiaires () {
        return stagiaireRepository.findAll();
    }

    public Stagiaire updateStagiaire(Long id, Stagiaire stagiaireDetails) {
        Stagiaire stagiaire = stagiaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stagiaire not found on :: " + id));

        // Utilise Optional pour vérifier si chaque champ est présent avant de le mettre à jour
        Optional.ofNullable(stagiaireDetails.getFirstName())
                .ifPresent(stagiaire::setFirstName); // Met à jour le prénom seulement s'il est fourni

        Optional.ofNullable(stagiaireDetails.getLastName())
                .ifPresent(stagiaire::setLastName); // Met à jour le nom de famille seulement s'il est fourni

        Optional.ofNullable(stagiaireDetails.getEmail())
                .ifPresent(stagiaire::setEmail); // Met à jour l'email seulement s'il est fourni

        // Pour les types primitifs comme boolean, utilisez un boolean wrapper class pour permettre null
        Optional.ofNullable(stagiaireDetails.isActive())
                .ifPresent(stagiaire::setActive); //// Only update if it's provided

        return stagiaireRepository.save(stagiaire);
    }


    public void deactivateStagiaire(Long id) {
        Stagiaire stagiaire = stagiaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stagiaire not found on :: " + id));
        stagiaire.setActive(false);
        stagiaireRepository.save(stagiaire);
    }

}
