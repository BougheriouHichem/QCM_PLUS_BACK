package com.pfe.qcm_plus_back.controller;

import com.pfe.qcm_plus_back.entity.Resultat;
import com.pfe.qcm_plus_back.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/resultats")
public class    ResultatController {

    @Autowired
    private ResultatService resultatService;

    @GetMapping
    public List<Resultat> getAllResultats() {
        return resultatService.getAllResultats();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resultat> getResultatById(@PathVariable Long id) {
        Optional<Resultat> resultat = resultatService.getResultatById(id);
        return resultat.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/stagiaire/{stagiaireId}")
    public List<Resultat> getResultatsByStagiaireId(@PathVariable Long stagiaireId) {
        return resultatService.getResultatsByStagiaireId(stagiaireId);
    }

    @GetMapping("/questionnaire/{questionnaireId}")
    public List<Resultat> getResultatsByQuestionnaireId(@PathVariable Long questionnaireId) {
        return resultatService.getResultatsByQuestionnaireId(questionnaireId);
    }

    @PostMapping("/calculer")
    public ResponseEntity<Resultat> calculerResultat(@RequestParam Long questionnaireId, @RequestParam Long stagiaireId) {
        Resultat resultat = resultatService.calculerResultat(questionnaireId, stagiaireId);
        return ResponseEntity.ok(resultat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResultat(@PathVariable Long id) {
        Optional<Resultat> resultat = resultatService.getResultatById(id);
        if (resultat.isPresent()) {
            resultatService.deleteResultat(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
