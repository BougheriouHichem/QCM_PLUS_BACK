package com.pfe.qcm_plus_back.controller;

import com.pfe.qcm_plus_back.entity.Reponse;
import com.pfe.qcm_plus_back.service.ReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reponses")
public class ReponseController {

    @Autowired
    private ReponseService reponseService;

    @GetMapping
    public List<Reponse> getAllReponses() {
        return reponseService.getAllReponses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reponse> getReponseById(@PathVariable Long id) {
        Optional<Reponse> reponse = reponseService.getReponseById(id);
        return reponse.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/stagiaire/{stagiaireId}")
    public List<Reponse> getReponsesByStagiaireId(@PathVariable Long stagiaireId) {
        return reponseService.getReponsesByStagiaireId(stagiaireId);
    }

    @GetMapping("/question/{questionId}")
    public List<Reponse> getReponsesByQuestionId(@PathVariable Long questionId) {
        return reponseService.getReponsesByQuestionId(questionId);
    }

    @PostMapping
    public Reponse createReponse(@RequestBody Reponse reponse) {
        return reponseService.saveReponse(reponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reponse> updateReponse(@PathVariable Long id, @RequestBody Reponse reponseDetails) {
        Optional<Reponse> optionalReponse = reponseService.getReponseById(id);

        if (optionalReponse.isPresent()) {
            Reponse reponseToUpdate = optionalReponse.get();
            reponseToUpdate.setReponses(reponseDetails.getReponses());
            reponseToUpdate.setQuestion(reponseDetails.getQuestion());
            reponseToUpdate.setStagiaire(reponseDetails.getStagiaire());
            final Reponse updatedReponse = reponseService.saveReponse(reponseToUpdate);
            return ResponseEntity.ok(updatedReponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReponse(@PathVariable Long id) {
        Optional<Reponse> reponse = reponseService.getReponseById(id);

        if (reponse.isPresent()) {
            reponseService.deleteReponse(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
