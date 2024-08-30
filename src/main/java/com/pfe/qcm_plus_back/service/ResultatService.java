package com.pfe.qcm_plus_back.service;

import com.pfe.qcm_plus_back.entity.*;
import com.pfe.qcm_plus_back.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResultatService {

    @Autowired
    private ResultatRepository resultatRepository;

    @Autowired
    private ReponseService reponseService;

    public List<Resultat> getAllResultats() {
        return resultatRepository.findAll();
    }

    public Optional<Resultat> getResultatById(Long id) {
        return resultatRepository.findById(id);
    }

    public List<Resultat> getResultatsByStagiaireId(Long stagiaireId) {
        return resultatRepository.findByStagiaireId(stagiaireId);
    }

    public List<Resultat> getResultatsByQuestionnaireId(Long questionnaireId) {
        return resultatRepository.findByQuestionnaireId(questionnaireId);
    }

    public Resultat saveResultat(Resultat resultat) {
        return resultatRepository.save(resultat);
    }

    public void deleteResultat(Long id) {
        resultatRepository.deleteById(id);
    }

    public Resultat calculerResultat(Long questionnaireId, Long stagiaireId) {
        // Récupère toutes les réponses du stagiaire pour ce questionnaire
        List<Reponse> reponses = reponseService.getReponsesByStagiaireId(stagiaireId).stream()
                .filter(reponse -> reponse.getQuestion().getQuestionnaire().getId().equals(questionnaireId))
                .collect(Collectors.toList());

        int score = 0;

        for (Reponse reponse : reponses) {
            Question question = reponse.getQuestion();
            if (reponse.getReponses().equals(question.getReponsesCorrectes())) {
                score++;
            }
        }

        // Créer le nouvel objet Resultat avec les informations correctes
        Resultat resultat = new Resultat();
        if (!reponses.isEmpty()) {
            resultat.setStagiaire(reponses.get(0).getStagiaire());
            resultat.setQuestionnaire(reponses.get(0).getQuestion().getQuestionnaire());
        } else {
            // Si aucune réponse n'est trouvée pour le stagiaire et le questionnaire, vous devez probablement gérer ce cas
            // ici en retournant une erreur ou en initialisant le questionnaire et le stagiaire d'une autre manière.
            Questionnaire questionnaire = new Questionnaire();
            questionnaire.setId(questionnaireId);
            resultat.setQuestionnaire(questionnaire);

            Stagiaire stagiaire = new Stagiaire();
            stagiaire.setId(stagiaireId);
            resultat.setStagiaire(stagiaire);
        }
        resultat.setResultatTotal(score);

        return saveResultat(resultat);
    }

}