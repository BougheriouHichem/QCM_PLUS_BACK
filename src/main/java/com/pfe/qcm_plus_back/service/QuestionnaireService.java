package com.pfe.qcm_plus_back.service;

import com.pfe.qcm_plus_back.entity.Questionnaire;
import com.pfe.qcm_plus_back.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    public Questionnaire createQuestionnaire(Questionnaire questionnaire) {
        return questionnaireRepository.save(questionnaire);
    }

    public Questionnaire updateQuestionnaire(Long id, Questionnaire questionnaireDetails) {
        Questionnaire questionnaire = questionnaireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Questionnaire not found on :: " + id));

        questionnaire.setName(questionnaireDetails.getName());
        questionnaire.setDescription(questionnaireDetails.getDescription());
        return questionnaireRepository.save(questionnaire);
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
