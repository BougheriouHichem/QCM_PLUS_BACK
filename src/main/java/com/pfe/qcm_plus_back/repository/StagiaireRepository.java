package com.pfe.qcm_plus_back.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pfe.qcm_plus_back.entity.Stagiaire;






@Repository
public interface StagiaireRepository extends JpaRepository<Stagiaire, Long> {
    Optional<Stagiaire> findById(Long StagiaireId);
    Stagiaire findByEmail(String email);
}