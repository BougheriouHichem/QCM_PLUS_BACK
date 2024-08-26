package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "reponse")
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "reponse_stagiaire",
        joinColumns = @JoinColumn(name = "reponse_id"),
        inverseJoinColumns = @JoinColumn(name = "stagiaire_id")
    )
    private Set<Stagiaire> stagiaires;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

    @ElementCollection
    private List<String> reponses;

    @Column(name = "correct", nullable = false)
    private boolean correct;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateModified;

}
