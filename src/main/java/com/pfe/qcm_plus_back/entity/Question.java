package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

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

    public Set<String> getChoix() {
        return choix;
    }

    public void setChoix(Set<String> choix) {
        this.choix = choix;
    }

    public int getNbreReponses() {
        return nbreReponses;
    }

    public void setNbreReponses(int nbreReponses) {
        this.nbreReponses = nbreReponses;
    }

    public Set<Integer> getReponsesCorrectes() {
        return reponsesCorrectes;
    }

    public void setReponsesCorrectes(Set<Integer> reponsesCorrectes) {
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
