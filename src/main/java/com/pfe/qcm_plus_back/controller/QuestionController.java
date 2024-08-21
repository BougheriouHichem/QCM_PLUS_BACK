package com.pfe.qcm_plus_back.controller;

import com.pfe.qcm_plus_back.entity.Question;
import com.pfe.qcm_plus_back.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public List<Question> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Optional<Question> question = questionService.getQuestionById(id);
        return question.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question) {
        Question savedQuestion = questionService.addQuestion(question);
        return ResponseEntity.ok(savedQuestion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestion(@PathVariable Long id, @RequestBody Question questionDetails) {
        Question updatedQuestion = questionService.updateQuestion(id, questionDetails);
        return ResponseEntity.ok(updatedQuestion);
    }

    @PutMapping("/{id}/questionnaire")
    public ResponseEntity<Question> updateQuestionnaire(@PathVariable Long id, @RequestBody Long newQuestionnaireId) {
        Question updatedQuestion = questionService.updateQuestionnaire(id, newQuestionnaireId);
        return ResponseEntity.ok(updatedQuestion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/questionnaire/{questionnaireId}")
    public List<Question> getQuestionsByQuestionnaireId(@PathVariable Long questionnaireId) {
        return questionService.getQuestionsByQuestionnaireId(questionnaireId);
    }


}
