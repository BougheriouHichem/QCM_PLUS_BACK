package com.PFE.QCM_PLUS.entity;

import java.util.Date;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Getter
@Setter
public class Reponse {

	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id_reponse;
    
	 private String responses_stagiaire;
	 @Temporal(TemporalType.DATE)
	 private Date date_created;
	 @Temporal(TemporalType.DATE)
	 private Date date_modified;
	 
	    @ManyToOne
	    @JoinColumn(name = "stagiaire_id")
	    private Stagiaire stagiaire;
	 
	 
	  @ManyToOne
	  @JoinColumn(name = "id_Question")
	  @JsonIgnore // Add this annotation to ignore the circular reference
	  private Question question;
	    
	 
	
}
