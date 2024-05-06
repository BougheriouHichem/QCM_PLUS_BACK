package com.PFE.QCM_PLUS.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Getter
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long AdminId;
    
	private String email;
	private String nom; 
	private String prénom;
	private String password;
	
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "admin")
    @JsonIgnore // Add this annotation to ignore the circular reference
    private List<Questionnaire> questionnaires;
   
	 @Temporal(TemporalType.DATE)
	private Date date_created;
	 @Temporal(TemporalType.DATE)
	private Date date_modified;
	private boolean Active; 


}
