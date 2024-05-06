package com.PFE.QCM_PLUS.service;

import com.PFE.QCM_PLUS.entity.Question;
import com.PFE.QCM_PLUS.entity.Reponse;
import com.PFE.QCM_PLUS.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ReponseService {

    @Autowired
    private ReponseRepository reponseRepository;

    public Reponse createReponse(Reponse reponse) {
        // Vérifier si le nombre de réponses de la question est dépassé
        Question question = reponse.getQuestion();
        int nbRepActuel = question.getReponses().size();
        int nbRepMax = question.getNbRep();
        if (nbRepActuel < nbRepMax) {
            reponse.setDate_created(new Date());
            return reponseRepository.save(reponse);
        } else {
            throw new RuntimeException("Le nombre maximum de réponses pour cette question est déjà atteint.");
        }
    }

    public Reponse updateReponse(Long reponseId, Reponse reponseDetails) {
        Optional<Reponse> optionalReponse = reponseRepository.findById(reponseId);
        if (optionalReponse.isPresent()) {
            Reponse existingReponse = optionalReponse.get();
            existingReponse.setResponses_stagiaire(reponseDetails.getResponses_stagiaire());
            existingReponse.setDate_modified(new Date());
            return reponseRepository.save(existingReponse);
        } else {
            throw new RuntimeException("Réponse non trouvée avec l'identifiant : " + reponseId);
        }
    }

    public void deleteReponse(Long reponseId) {
        reponseRepository.deleteById(reponseId);
    }

    public Reponse getReponseById(Long reponseId) {
        Optional<Reponse> optionalReponse = reponseRepository.findById(reponseId);
        if (optionalReponse.isPresent()) {
            return optionalReponse.get();
        } else {
            throw new RuntimeException("Réponse non trouvée avec l'identifiant : " + reponseId);
        }
    }
}
