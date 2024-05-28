package com.pfe.qcm_plus_back.controller;

import com.pfe.qcm_plus_back.entity.Questionnaire;
import com.pfe.qcm_plus_back.service.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/questionnaires")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @PostMapping
    public Questionnaire createQuestionnaire (@RequestBody Questionnaire questionnaire){
        return questionnaireService.createQuestionnaire(questionnaire);
    }

    @GetMapping
    public List<Questionnaire> getAllQuestionnaires () {
        return questionnaireService.getAllQuestionnaires();
    }

    @GetMapping("/{id}")
    public Questionnaire getQuestionnaireById(@PathVariable Long id) {
        return questionnaireService.getQuestionnaireById(id);
    }

    @PutMapping("/{id}")
    public Questionnaire updateQuestionnaire(@PathVariable Long id, @RequestBody Questionnaire questionnaireDetails) {
        return questionnaireService.updateQuestionnaire(id, questionnaireDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionnaire(@PathVariable Long id) {
        questionnaireService.deleteQuestionnaire(id);
        return ResponseEntity.ok().build();
    }
}
