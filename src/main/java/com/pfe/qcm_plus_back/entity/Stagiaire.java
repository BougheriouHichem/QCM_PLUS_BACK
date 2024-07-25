package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "stagiaire")
public class Stagiaire extends Utilisateur{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean isActive = true;

    // isActive() ==> getter of isActive

    //public void setActive(boolean active) setter
}
