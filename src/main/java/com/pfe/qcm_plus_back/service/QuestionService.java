package com.pfe.qcm_plus_back.service;

import com.pfe.qcm_plus_back.entity.Question;
import com.pfe.qcm_plus_back.entity.Questionnaire;
import com.pfe.qcm_plus_back.repository.QuestionRepository;
import com.pfe.qcm_plus_back.repository.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionnaireRepository questionnaireRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionnaireRepository questionnaireRepository) {
        this.questionRepository = questionRepository;
        this.questionnaireRepository = questionnaireRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public Question addQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question questionDetails) {
        return questionRepository.findById(id)
                .map(question -> {
                    question.setQuestionTexte(questionDetails.getQuestionTexte());
                    question.setChoix(questionDetails.getChoix());
                    question.setNbreReponses(questionDetails.getNbreReponses());
                    question.setReponsesCorrectes(questionDetails.getReponsesCorrectes());
                    return questionRepository.save(question);
                })
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    // Ajout de la méthode pour modifier l'appartenance d'une question à un autre questionnaire
    public Question updateQuestionnaire(Long questionId, Long newQuestionnaireId) {
        // Récupérer la question par son ID
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        // Récupérer le nouveau questionnaire par son ID
        Questionnaire newQuestionnaire = questionnaireRepository.findById(newQuestionnaireId)
                .orElseThrow(() -> new RuntimeException("Questionnaire not found"));

        // Modifier l'appartenance de la question
        question.setQuestionnaire(newQuestionnaire);

        // Sauvegarder la question mise à jour
        return questionRepository.save(question);
    }
}
