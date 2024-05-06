package com.PFE.QCM_PLUS.entity;

import java.util.Date;

import javax.persistence.*;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
public class Resultat {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resultatId;
	
	    @ManyToOne
	    @JoinColumn(name = "stagiaire_id")
	    private Stagiaire stagiaire;

	    @ManyToOne
	    @JoinColumn(name = "questionnaire_id")
	    private Questionnaire questionnaire;
	    
	 @Temporal(TemporalType.DATE)
	private Date date_created;
	 @Temporal(TemporalType.DATE)
	private Date date_modified;

}
