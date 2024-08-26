package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.*;
import java.util.Arrays;
import java.util.Objects;


@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    @Column(name = "question_texte", nullable = false)
    private String questionTexte;

    @Column(name = "nbre_reponses", nullable = false)
    private int nbreReponses;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Reponse[] choix;

    // Constructeurs, getters, setters, toString, equals, hashCode
    public Question() {}

    public Question(Questionnaire questionnaire, String questionTexte, int nbreReponses) {
        this.questionnaire = questionnaire;
        this.questionTexte = questionTexte;
        this.nbreReponses = nbreReponses;
        this.choix = new Reponse[nbreReponses]; // Initialisation du tableau avec la taille de nbreReponses
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String getQuestionTexte() {
        return questionTexte;
    }

    public void setQuestionTexte(String questionTexte) {
        this.questionTexte = questionTexte;
    }

    public int getNbreReponses() {
        return nbreReponses;
    }

    public void setNbreReponses(int nbreReponses) {
        this.nbreReponses = nbreReponses;
        this.choix = new Reponse[nbreReponses]; // Mise à jour du tableau lorsque nbreReponses change
    }

    public Reponse[] getChoix() {
        return choix;
    }

    public void setChoix(Reponse[] choix) {
        if (choix.length > nbreReponses) {
            throw new IllegalArgumentException("Le nombre de réponses ne doit pas dépasser " + nbreReponses);
        }
        this.choix = choix;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionnaire=" + questionnaire +
                ", questionTexte='" + questionTexte + '\'' +
                ", nbreReponses=" + nbreReponses +
                ", choix=" + Arrays.toString(choix) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id.equals(question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


