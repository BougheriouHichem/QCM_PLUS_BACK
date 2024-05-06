package com.PFE.QCM_PLUS.entity;



import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Questionnaire {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_questionnaire;
    
	private String Q_Name;
	private String Description;
	
	    @ManyToOne
	    @JoinColumn(name = "AdminId")
	    @JsonIgnore // Add this annotation to ignore the circular reference
	    private Admin admin;
	    
		  @OneToMany(mappedBy = "questionnaire")
		    private List<Resultat> resultats;
	    
	   @OneToMany(cascade = CascadeType.ALL, mappedBy = "questionnaire")
	   @JsonIgnore // Add this annotation to ignore the circular reference
	   private List<Question> questions;
	   

}
