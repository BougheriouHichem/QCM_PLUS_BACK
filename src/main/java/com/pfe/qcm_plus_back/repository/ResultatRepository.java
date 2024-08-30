package com.pfe.qcm_plus_back.repository;

import com.pfe.qcm_plus_back.entity.Resultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultatRepository extends JpaRepository<Resultat, Long> {
    List<Resultat> findByStagiaireId(Long stagiaireId);
    List<Resultat> findByQuestionnaireId(Long questionnaireId);
}

