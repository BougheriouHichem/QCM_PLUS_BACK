package com.PFE.QCM_PLUS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.PFE.QCM_PLUS.entity.Stagiaire;



@Repository
public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
    Optional<Stagiaire> findById(Long StagiaireId);
    Stagiaire findByEmail(String email);
}