package com.pfe.qcm_plus_back.repository;

import com.pfe.qcm_plus_back.entity.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse, Long> {
	 Reponse findReponseById(Long id);
	    List<Reponse> findReponseByStagiaireId(Long stagiaireId);
	    List<Reponse> findReponseByQuestionId(Long questionId);
		List<Reponse> findByStagiaireIdAndQuestionnaireId(Long stagiaireId, Long questionnaireId);
		List<Reponse> findByStagiairesId(Long stagiaireId);
}
