package com.pfe.qcm_plus_back.controller;

import com.pfe.qcm_plus_back.entity.Stagiaire;
import com.pfe.qcm_plus_back.service.StagiaireService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stagiaires")
@Slf4j
public class StagiaireController {

    private static final Logger logger = LoggerFactory.getLogger(StagiaireController.class);

    @Autowired
    private StagiaireService stagiaireService;

    @GetMapping
    public List<Stagiaire> getAllStagiaires () {
        return stagiaireService.getAllStagiaires();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stagiaire> getStagiaireById(@PathVariable Long id) {
        Optional<Stagiaire> stagiaire = stagiaireService.getStagiaireById(id);
        return stagiaire.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<?> addStagiaire(@RequestBody Stagiaire stagiaire) {
        try {
            Stagiaire newStagiaire = stagiaireService.addStagiaire(stagiaire);
            return ResponseEntity.ok(newStagiaire);
        } catch (Exception e) {
            logger.error("Error adding stagiaire", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding stagiaire: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public Stagiaire updateStagiaire(@PathVariable Long id, @RequestBody Stagiaire stagiaireDetails) {
        return stagiaireService.updateStagiaire(id, stagiaireDetails);
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<?> deactivateStagiaire(@PathVariable Long id) {
        stagiaireService.deactivateStagiaire(id);
        return ResponseEntity.ok().build();
    }

}

