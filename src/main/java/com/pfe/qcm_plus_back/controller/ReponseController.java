package com.pfe.qcm_plus_back.controller;

import com.pfe.qcm_plus_back.entity.Reponse;
import com.pfe.qcm_plus_back.service.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reponses")
public class ReponseController {

    private final ReponseService reponseService;

    @Autowired
    public ReponseController(ReponseService reponseService) {
        this.reponseService = reponseService;
    }

    // Créer une nouvelle réponse
    @PostMapping
    public ResponseEntity<Reponse> createReponse(@RequestBody Reponse reponse) {
        Reponse nouvelleReponse = reponseService.addReponse(reponse);
        return new ResponseEntity<>(nouvelleReponse, HttpStatus.CREATED);
    }

    // Récupérer toutes les réponses
    @GetMapping
    public ResponseEntity<List<Reponse>> getAllReponses() {
        List<Reponse> reponses = reponseService.getAllReponses();
        return new ResponseEntity<>(reponses, HttpStatus.OK);
    }

    // Récupérer une réponse par ID
    @GetMapping("/{id}")
    public ResponseEntity<Reponse> getReponseById(@PathVariable("id") Long id) {
        Optional<Reponse> reponse = reponseService.getReponseById(id);
        return reponse.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour une réponse existante
    @PutMapping("/{id}")
    public ResponseEntity<Reponse> updateReponse(@PathVariable("id") Long id, @RequestBody Reponse reponseDetails) {
        try {
            Reponse reponseMiseAJour = reponseService.updateReponse(id, reponseDetails);
            return new ResponseEntity<>(reponseMiseAJour, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer une réponse par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReponse(@PathVariable("id") Long id) {
        try {
            reponseService.deleteReponse(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Ajouter un stagiaire à une réponse spécifique
    @PostMapping("/{reponseId}/stagiaires/{stagiaireId}")
    public ResponseEntity<Reponse> ajouterStagiaire(@PathVariable("reponseId") Long reponseId,
                                                     @PathVariable("stagiaireId") Long stagiaireId) {
        try {
            Reponse reponseMiseAJour = reponseService.ajouterStagiaire(reponseId, stagiaireId);
            return new ResponseEntity<>(reponseMiseAJour, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
