package com.pfe.qcm_plus_back.service;

import com.pfe.qcm_plus_back.entity.Question;
import com.pfe.qcm_plus_back.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question findById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found on :: " + id));
    }

    public Question save(Question question) {
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, Question questionDetails) {
        Question existingQuestion = findById(id); // Ensures we throw if not found

        Optional.ofNullable(questionDetails.getQuestionTexte()).ifPresent(existingQuestion::setQuestionTexte);
        Optional.ofNullable(questionDetails.getChoix()).ifPresent(existingQuestion::setChoix);
        Optional.ofNullable(questionDetails.getNbreReponses()).ifPresent(existingQuestion::setNbreReponses);
        Optional.ofNullable(questionDetails.getReponsesCorrectes()).ifPresent(existingQuestion::setReponsesCorrectes);

        return questionRepository.save(existingQuestion);
    }

    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}
