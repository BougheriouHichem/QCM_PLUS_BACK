package com.pfe.qcm_plus_back.repository;

import com.pfe.qcm_plus_back.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
}
