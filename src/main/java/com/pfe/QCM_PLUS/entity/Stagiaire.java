package com.PFE.QCM_PLUS.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Data
@Entity
@Getter
@Setter
public class Stagiaire {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long StagiaireId;
    
	private String email;
	private String nom; 
	private String prénom;
	private String password;
	 @Temporal(TemporalType.DATE)
	private Date date_created;
	 @Temporal(TemporalType.DATE)
	private Date date_modified;
	private boolean Active; 
     
	  @OneToMany(mappedBy = "stagiaire")
	    private List<Reponse> reponses;
	
	 @OneToMany(mappedBy = "stagiaire")
	 private List<Resultat> resultats;
	 

	    

}
