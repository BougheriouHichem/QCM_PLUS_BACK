package com.pfe.qcm_plus_back.service;

import com.pfe.qcm_plus_back.entity.Reponse;
import com.pfe.qcm_plus_back.entity.Stagiaire;
import com.pfe.qcm_plus_back.repository.ReponseRepository;
import com.pfe.qcm_plus_back.repository.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReponseService {

    private final ReponseRepository reponseRepository;
    private final StagiaireRepository stagiaireRepository;

    @Autowired
    public ReponseService(ReponseRepository reponseRepository, StagiaireRepository stagiaireRepository) {
        this.reponseRepository = reponseRepository;
        this.stagiaireRepository = stagiaireRepository;
    }
    
    // Obtenir les réponses par ID de stagiaire
    public List<Reponse> getReponsesByStagiaire(Long stagiaireId) {
        return reponseRepository.findByStagiairesId(stagiaireId);
    }

    // Create
    public Reponse addReponse(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    // Read
    public List<Reponse> getAllReponses() {
        return reponseRepository.findAll();
    }

    public Optional<Reponse> getReponseById(Long id) {
        return reponseRepository.findById(id);
    }

    // Update
    public Reponse updateReponse(Long id, Reponse reponseDetails) {
        return reponseRepository.findById(id)
                .map(reponse -> {
                    reponse.setReponses(reponseDetails.getReponses());
                    reponse.setCorrect(reponseDetails.isCorrect());
                    return reponseRepository.save(reponse);
                })
                .orElseThrow(() -> new RuntimeException("Reponse not found"));
    }

    // Delete
    public void deleteReponse(Long id) {
        reponseRepository.deleteById(id);
    }

    // Méthode pour ajouter un stagiaire à une réponse
    public Reponse ajouterStagiaire(Long reponseId, Long stagiaireId) {
        Reponse reponse = reponseRepository.findById(reponseId)
                .orElseThrow(() -> new RuntimeException("Reponse not found"));

        Stagiaire stagiaire = stagiaireRepository.findById(stagiaireId)
                .orElseThrow(() -> new RuntimeException("Stagiaire not found"));

        reponse.getStagiaires().add(stagiaire);
        return reponseRepository.save(reponse);
    }
}

