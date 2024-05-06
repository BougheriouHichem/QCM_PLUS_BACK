package com.PFE.QCM_PLUS.service;

import com.PFE.QCM_PLUS.entity.Question;
import com.PFE.QCM_PLUS.entity.Reponse;
import com.PFE.QCM_PLUS.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    public Question createQuestion(Question question) {
        question.setDate_created(new Date());
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long questionId, Question questionDetails) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            Question existingQuestion = optionalQuestion.get();
            existingQuestion.setTxtQuestion(questionDetails.getTxtQuestion());
            existingQuestion.setNbRep(questionDetails.getNbRep());
            existingQuestion.setDate_modified(new Date());
            return questionRepository.save(existingQuestion);
        } else {
            throw new RuntimeException("Question not found with id: " + questionId);
        }
    }

    public void deleteQuestion(Long questionId) {
        questionRepository.deleteById(questionId);
    }

    public Question getQuestionById(Long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);
        if (optionalQuestion.isPresent()) {
            return optionalQuestion.get();
        } else {
            throw new RuntimeException("Question not found with id: " + questionId);
        }
    }

    public List<Reponse> getReponsesByQuestionId(Long questionId) {
        Question question = getQuestionById(questionId);
        return question.getReponses();
    }
}
