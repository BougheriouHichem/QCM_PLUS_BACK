package com.pfe.qcm_plus_back.service;

import com.pfe.qcm_plus_back.entity.Reponse;
import com.pfe.qcm_plus_back.entity.Question;
import com.pfe.qcm_plus_back.entity.Questionnaire;
import com.pfe.qcm_plus_back.repository.QuestionRepository;
import com.pfe.qcm_plus_back.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ResultatService {

    private final ReponseService reponseService;
    private final QuestionRepository questionRepository;
    private final QuestionnaireRepository questionnaireRepository;

    @Autowired
    public ResultatService(ReponseService reponseService, QuestionRepository questionRepository,
                           QuestionnaireRepository questionnaireRepository) {
        this.reponseService = reponseService;
        this.questionRepository = questionRepository;
        this.questionnaireRepository = questionnaireRepository;
    }

    // Calculer le score pour un stagiaire à un questionnaire
    public int calculateScore(Long stagiaireId, Long questionnaireId) {
        // Récupérer le questionnaire par son ID (non utilisé dans le calcul mais nécessaire pour récupérer les questions)
        Questionnaire questionnaire = questionnaireRepository.findById(questionnaireId)
                .orElseThrow(() -> new RuntimeException("Questionnaire not found"));

        // Récupérer les questions du questionnaire
        List<Question> questions = questionRepository.findAllByQuestionnaireId(questionnaireId);

        // Récupérer les réponses correctes pour chaque question
        Set<Long> reponsesCorrectesIds = questions.stream()
                .flatMap(question -> 
                    List.of(question.getChoix()).stream() // Convertir le tableau de réponses en flux
                )
                .filter(Reponse::isCorrect) // Filtrer les réponses correctes
                .map(Reponse::getId) // Récupérer les IDs des réponses correctes
                .collect(Collectors.toSet());

        // Récupérer les réponses du stagiaire pour le questionnaire
        List<Reponse> reponsesStagiaire = reponseService.getReponsesByStagiaire(stagiaireId);

        // Compter le nombre de réponses correctes données par le stagiaire
        long nombreReponsesCorrectesDonnees = reponsesStagiaire.stream()
                .flatMap(reponse -> reponse.getReponses().stream()) // Convertir les réponses en flux
                .map(Long::parseLong) // Convertir les réponses en Long si nécessaire
                .filter(reponsesCorrectesIds::contains) // Vérifier si les réponses données sont correctes
                .count();

        // Retourner le score calculé
        return (int) nombreReponsesCorrectesDonnees;
    }
}


