package com.PFE.QCM_PLUS.service;


import com.PFE.QCM_PLUS.entity.Stagiaire;
import com.PFE.QCM_PLUS.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class StagiaireService {

    @Autowired
    private StagiaireRepository stagiaireRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder; 

    public Stagiaire createStagiaire(Stagiaire stagiaire) {
    	stagiaire.setPassword(passwordEncoder.encode(stagiaire.getPassword()));
        stagiaire.setDate_created(new Date());
        return stagiaireRepository.save(stagiaire);
    }

    public Stagiaire updateStagiaire(Long stagiaireId, Stagiaire stagiaireDetails) {
        Optional<Stagiaire> optionalStagiaire = stagiaireRepository.findById(stagiaireId);
        if (optionalStagiaire.isPresent()) {
            Stagiaire existingStagiaire = optionalStagiaire.get();
            existingStagiaire.setEmail(stagiaireDetails.getEmail());
            existingStagiaire.setNom(stagiaireDetails.getNom());
            existingStagiaire.setPrénom(stagiaireDetails.getPrénom());
            existingStagiaire.setPassword(stagiaireDetails.getPassword());
            existingStagiaire.setDate_modified(new Date());
            existingStagiaire.setActive(stagiaireDetails.isActive());
            return stagiaireRepository.save(existingStagiaire);
        } else {
            throw new RuntimeException("Stagiaire not found with id: " + stagiaireId);
        }
    }

    public void deleteStagiaire(Long stagiaireId) {
        stagiaireRepository.deleteById(stagiaireId);
    }

    public Stagiaire getStagiaireById(Long stagiaireId) {
        Optional<Stagiaire> optionalStagiaire = stagiaireRepository.findById(stagiaireId);
        if (optionalStagiaire.isPresent()) {
            return optionalStagiaire.get();
        } else {
            throw new RuntimeException("Stagiaire not found with id: " + stagiaireId);
        }
    }

    public List<Stagiaire> getAllStagiaires() {
        return stagiaireRepository.findAll();
    }
    
}
