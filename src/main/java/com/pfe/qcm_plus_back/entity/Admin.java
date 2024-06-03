package com.pfe.qcm_plus_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("ADMIN")
@Table(name = "admin")
public class Admin extends Utilisateur{

 
}
