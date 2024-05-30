package com.pfe.qcm_plus_back.services;

import com.pfe.qcm_plus_back.entity.Questionnaire;
import com.pfe.qcm_plus_back.repository.QuestionnaireRepository;
import com.pfe.qcm_plus_back.service.QuestionnaireService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionnaireServiceTest {

    @Mock
    private QuestionnaireRepository questionnaireRepository;

    @InjectMocks
    private QuestionnaireService questionnaireService;

    @Test
    void testCreateQuestionnaire(){
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName("Java Basics");
        when(questionnaireRepository.save(any(Questionnaire.class))).thenReturn(questionnaire);

        Questionnaire created = questionnaireService.createQuestionnaire(questionnaire);
        assertNotNull(created);
        assertEquals("Java Basics",created.getName());
    }

    @Test
    void testUpdateQuestionnaire(){
        Questionnaire existingQuestionnaire = new Questionnaire();
        existingQuestionnaire.setId(1L);
        existingQuestionnaire.setName("Java Basics");

        Questionnaire newDetails = new Questionnaire();
        newDetails.setName("Advanced Java");

        when(questionnaireRepository.findById(1L)).thenReturn(Optional.of(existingQuestionnaire));
        when(questionnaireRepository.save(any(Questionnaire.class))).thenReturn(newDetails);

        Questionnaire updated = questionnaireService.updateQuestionnaire(1L, newDetails);
        assertNotNull(updated);
        assertEquals("Advanced Java", updated.getName());
    }

    @Test
    void testDeleteQuestionnaire(){
        Questionnaire toDelete = new Questionnaire();
        toDelete.setId(1L);

        when(questionnaireRepository.findById(1L)).thenReturn(Optional.of(toDelete));
        questionnaireService.deleteQuestionnaire(1L);
        verify(questionnaireRepository).delete(toDelete);
    }

}
