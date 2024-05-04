package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "reponse")
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "stagiaire_id")
    private Stagiaire stagiaire;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ElementCollection
    private List<String> reponses;

}
