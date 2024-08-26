package com.pfe.qcm_plus_back.controller;

import com.pfe.qcm_plus_back.service.ResultatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/resultats")
public class ResultatController {

    private final ResultatService resultatService;

    @Autowired
    public ResultatController(ResultatService resultatService) {
        this.resultatService = resultatService;
    }

    /**
     * Endpoint pour obtenir le score d'un stagiaire à un questionnaire spécifique.
     *
     * @param stagiaireId ID du stagiaire
     * @param questionnaireId ID du questionnaire
     * @return Le score du stagiaire pour le questionnaire
     */
    @GetMapping("/score")
    public int getScore(@RequestParam Long stagiaireId, @RequestParam Long questionnaireId) {
        return resultatService.calculateScore(stagiaireId, questionnaireId);
    }
}
