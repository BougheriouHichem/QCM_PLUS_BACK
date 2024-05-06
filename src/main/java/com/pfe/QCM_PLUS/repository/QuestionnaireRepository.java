package com.PFE.QCM_PLUS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.PFE.QCM_PLUS.entity.Questionnaire;



@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    Optional<Questionnaire> findById(Long id_questionnaire);
}