package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "resultat")
public class Resultat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stagiaire_id", nullable = false)
    private Stagiaire stagiaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_id", nullable = false)
    private Questionnaire questionnaire;

    @Column(name = "score")
    private int score;

    @Column(name = "date_passation")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date datePassation;

    @Column(name = "commentaire")
    private String commentaire;

    // Constructeurs, getters, setters, toString, equals, hashCode
    public Resultat() {}

    public Resultat(Stagiaire stagiaire, Questionnaire questionnaire, int score, String commentaire) {
        this.stagiaire = stagiaire;
        this.questionnaire = questionnaire;
        this.score = score;
        this.commentaire = commentaire;
    }

    @Override
    public String toString() {
        return "Resultat{" +
                "id=" + id +
                ", stagiaire=" + stagiaire +
                ", questionnaire=" + questionnaire +
                ", score=" + score +
                ", datePassation=" + datePassation +
                ", commentaire='" + commentaire + '\'' +
                '}';
    }
}
