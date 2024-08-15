package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
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

    @ElementCollection
    @CollectionTable(name = "choix", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "choix")
    private Set<String> choix;

    @Column(name = "nbre_reponses", nullable = false)
    private int nbreReponses;

    @ElementCollection
    @CollectionTable(name = "reponses_correctes", joinColumns = @JoinColumn(name = "question_id"))
    @Column(name = "reponse_correcte")
    private Set<Integer> reponsesCorrectes;

    // Constructeurs, getters, setters, toString, equals, hashCode
    public Question() {}

    public Question(Questionnaire questionnaire, String questionTexte, Set<String> choix, int nbreReponses, Set<Integer> reponsesCorrectes) {
        this.questionnaire = questionnaire;
        this.questionTexte = questionTexte;
        this.choix = choix;
        this.nbreReponses = nbreReponses;
        this.reponsesCorrectes = reponsesCorrectes;
    }


    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionnaire=" + questionnaire +
                ", questionTexte='" + questionTexte + '\'' +
                ", choix=" + choix +
                ", nbreReponses=" + nbreReponses +
                ", reponsesCorrectes=" + reponsesCorrectes +
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
