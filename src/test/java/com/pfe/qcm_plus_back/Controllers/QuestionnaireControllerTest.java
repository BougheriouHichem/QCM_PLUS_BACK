package com.pfe.qcm_plus_back.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfe.qcm_plus_back.controller.QuestionnaireController;
import com.pfe.qcm_plus_back.entity.Questionnaire;
import com.pfe.qcm_plus_back.service.QuestionnaireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class QuestionnaireControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionnaireService questionnaireService;

    @Test
    public void testCreateQuestionnaire() throws Exception {
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName("Java Basics");
        when(questionnaireService.createQuestionnaire(any(Questionnaire.class))).thenReturn(questionnaire);

        mockMvc.perform(post("/api/questionnaires")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(questionnaire)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteQuestionnaire() throws Exception {
        doNothing().when(questionnaireService).deleteQuestionnaire(1L);

        mockMvc.perform(delete("/api/questionnaires/1"))
                .andExpect(status().isOk());
    }

}
