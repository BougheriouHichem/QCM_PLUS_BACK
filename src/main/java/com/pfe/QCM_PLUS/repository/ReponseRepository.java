package com.PFE.QCM_PLUS.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.PFE.QCM_PLUS.entity.Reponse;



@Repository
public interface ReponseRepository extends JpaRepository<Reponse, Long> {
    Optional<Reponse> findById(Long id_reponse);
}