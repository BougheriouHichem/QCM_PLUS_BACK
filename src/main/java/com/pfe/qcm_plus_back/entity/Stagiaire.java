package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "stagiaire")
public class Stagiaire extends Utilisateur{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
}
