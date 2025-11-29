package com.EvalTrack.Entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

@Entity
public class Section {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer sectionId;
	private String nomSection;
	
	 
        
	    @JsonIgnore
	    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Etudiant> listeEtudiants;
	    @JsonIgnore
	    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Module> listeModules;

	    public Section() {}
	    

	    public Section(Integer sectionId) {
			super();
			this.sectionId = sectionId;
		}


		public Section(String nomSection,Integer semestre) {
	        this.nomSection = nomSection;
	       
	      
	    }

	    public int getIdSection() {
	        return sectionId;
	    }

	    public void setIdSection(int idSection) {
	        this.sectionId = idSection;
	    }

	    public Section(Integer sectionId, String nomSection, int semestre, List<Etudiant> listeEtudiants,
				List<Module> listeModules) {
			super();
			sectionId = sectionId;
			this.nomSection = nomSection;
		
			this.listeEtudiants = listeEtudiants;
			this.listeModules = listeModules;
		}

		public String getNomSection() {
	        return nomSection;
	    }

	    public void setNomSection(String nomSection) {
	        this.nomSection = nomSection;
	    }

	  
	  

	    public List<Etudiant> getListeEtudiants() {
	        return listeEtudiants;
	    }

	    public void setListeEtudiants(List<Etudiant> listeEtudiants) {
	        this.listeEtudiants = listeEtudiants;
	    }

	    public List<Module> getListeModules() {
	        return listeModules;
	    }

	    public void setListeModules(List<Module> listeModules) {
	        this.listeModules = listeModules;
	    }




	    
	}

