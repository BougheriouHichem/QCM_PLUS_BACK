package com.pfe.qcm_plus_back;

import com.pfe.qcm_plus_back.entity.Question;
import com.pfe.qcm_plus_back.service.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;

    @Test
    public void testFindAllQuestions() {
        assertDoesNotThrow(() -> {
            List<Question> questions = questionService.findAll();
            assertNotNull(questions);
            assertFalse(questions.isEmpty());
        });
    }
}

