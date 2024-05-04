package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "questionnaire")
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "questionnaire", cascade = CascadeType.ALL)
    private List<Question> questions;
}
