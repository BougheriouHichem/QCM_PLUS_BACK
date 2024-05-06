package com.PFE.QCM_PLUS.service;


import com.PFE.QCM_PLUS.entity.Admin;
import com.PFE.QCM_PLUS.entity.Question;
import com.PFE.QCM_PLUS.entity.Questionnaire;
import com.PFE.QCM_PLUS.repository.AdminRepository;
import com.PFE.QCM_PLUS.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionnaireService {

    @Autowired
    private QuestionnaireRepository questionnaireRepository;
    
    @Autowired
    private AdminRepository adminRepository; 

    public Questionnaire createQuestionnaireByAdmin(Questionnaire questionnaire, Long adminId) {
        Optional<Admin> optionalAdmin = adminRepository.findById(adminId);
        if (optionalAdmin.isPresent()) {
            Admin admin = optionalAdmin.get();
            questionnaire.setAdmin(admin);
            return questionnaireRepository.save(questionnaire);
        } else {
            throw new RuntimeException("Admin not found with id: " + adminId);
        }
    }


    public Questionnaire updateQuestionnaire(Long questionnaireId, Questionnaire questionnaireDetails) {
        Optional<Questionnaire> optionalQuestionnaire = questionnaireRepository.findById(questionnaireId);
        if (optionalQuestionnaire.isPresent()) {
            Questionnaire existingQuestionnaire = optionalQuestionnaire.get();
            existingQuestionnaire.setQ_Name(questionnaireDetails.getQ_Name());
            existingQuestionnaire.setDescription(questionnaireDetails.getDescription());
            existingQuestionnaire.setQuestions(questionnaireDetails.getQuestions());
            return questionnaireRepository.save(existingQuestionnaire);
        } else {
            throw new RuntimeException("Questionnaire not found with id: " + questionnaireId);
        }
    }

    public void deleteQuestionnaire(Long questionnaireId) {
        questionnaireRepository.deleteById(questionnaireId);
    }

    public Questionnaire getQuestionnaireById(Long questionnaireId) {
        Optional<Questionnaire> optionalQuestionnaire = questionnaireRepository.findById(questionnaireId);
        if (optionalQuestionnaire.isPresent()) {
            return optionalQuestionnaire.get();
        } else {
            throw new RuntimeException("Questionnaire not found with id: " + questionnaireId);
        }
    }

    public List<Question> getQuestionsByQuestionnaireId(Long questionnaireId) {
        Questionnaire questionnaire = getQuestionnaireById(questionnaireId);
        return questionnaire.getQuestions();
    }
}
