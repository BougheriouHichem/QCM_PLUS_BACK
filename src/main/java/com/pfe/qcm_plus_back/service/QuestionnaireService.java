package com.pfe.qcm_plus_back.service;

import com.pfe.qcm_plus_back.entity.Questionnaire;
import com.pfe.qcm_plus_back.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    public Questionnaire createQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public Questionnaire updateQuestionnaire(Long id, Questionnaire questionnaireDetails) {
        Questionnaire existingQuestionnaire = questionnaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questionnaire not found on :: " + id));

        // Mise à jour du nom si présent
        Optional.ofNullable(questionnaireDetails.getName())
                .ifPresent(existingQuestionnaire::setName);  // Met à jour le nom seulement s'il est fourni

        // Mise à jour de la description si présente
        Optional.ofNullable(questionnaireDetails.getDescription())
                .ifPresent(existingQuestionnaire::setDescription);  // Met à jour la description seulement si elle est fournie

        return questionnaireRepository.save(existingQuestionnaire);
    }


    public void deleteQuestionnaire(Long id) {
        Questionnaire questionnaire = questionnaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questionnaire not found on :: " + id));
        questionnaireRepository.delete(questionnaire);
    }

    public List<Questionnaire> getAllQuestionnaires() {
        return questionnaireRepository.findAll();
    }

    public Questionnaire getQuestionnaireById(Long id) {
        return questionnaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questionnaire not found on :: " + id));
    }
}
