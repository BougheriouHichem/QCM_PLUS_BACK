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
public class Question {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_Question;
	
	private String TxtQuestion;
	private int NbRep;
	 @Temporal(TemporalType.DATE)
	private Date date_created;
	 @Temporal(TemporalType.DATE)
	private Date date_modified;
	 
	    @ManyToOne
	    @JoinColumn(name = "id_questionnaire")
	    @JsonIgnore // Add this annotation to ignore the circular reference
	    private Questionnaire questionnaire;
	    
	    
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "question")
		@JsonIgnore // Add this annotation to ignore the circular reference
		private List<Reponse> reponses;
		    

}
