package com.PFE.QCM_PLUS.service;

import com.PFE.QCM_PLUS.entity.Resultat;
import com.PFE.QCM_PLUS.repository.ResultatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResultatService {

    @Autowired
    private ResultatRepository resultatRepository;

    public List<Resultat> getResultatByStagiaireAndQuestionnaire(Long stagiaireId, Long questionnaireId) {
        return resultatRepository.findByStagiaire_StagiaireIdAndQuestionnaire_IdQuestionnaire(stagiaireId, questionnaireId);
    }
}

