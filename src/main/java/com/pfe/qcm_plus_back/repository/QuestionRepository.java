package com.pfe.qcm_plus_back.repository;

import com.pfe.qcm_plus_back.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    // Vous pouvez ajouter des méthodes de requêtes personnalisées ici si nécessaire
    List<Question> findAllByQuestionnaireId(Long id);
}
