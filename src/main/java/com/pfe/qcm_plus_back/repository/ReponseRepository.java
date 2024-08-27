package com.pfe.qcm_plus_back.repository;

import com.pfe.qcm_plus_back.entity.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse, Long> {
    List<Reponse> findByStagiaireId(Long stagiaireId);
    List<Reponse> findByQuestionId(Long questionId);
}