package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("STAGIAIRE")
@Table(name = "stagiaire")
public class Stagiaire extends Utilisateur{

  
}
