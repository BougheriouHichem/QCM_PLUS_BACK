package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "admin")
public class Admin extends Utilisateur{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}