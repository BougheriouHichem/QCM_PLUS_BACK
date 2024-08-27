package com.pfe.qcm_plus_back.service;

import com.pfe.qcm_plus_back.entity.Reponse;
import com.pfe.qcm_plus_back.repository.ReponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReponseService {

    @Autowired
    private ReponseRepository reponseRepository;

    public List<Reponse> getAllReponses() {
        return reponseRepository.findAll();
    }

    public Optional<Reponse> getReponseById(Long id) {
        return reponseRepository.findById(id);
    }

    public List<Reponse> getReponsesByStagiaireId(Long stagiaireId) {
        return reponseRepository.findByStagiaireId(stagiaireId);
    }

    public List<Reponse> getReponsesByQuestionId(Long questionId) {
        return reponseRepository.findByQuestionId(questionId);
    }

    public Reponse saveReponse(Reponse reponse) {
        return reponseRepository.save(reponse);
    }

    public void deleteReponse(Long id) {
        reponseRepository.deleteById(id);
    }
}
