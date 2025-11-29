package com.EvalTrack.Entities;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Module {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModule;

    private String nomModule;
    private float moyenne;
    private int semestre;
    private float coefModule;
    
    @JsonIgnore
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private List<Matiére> listeMatieres;
    
    
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionId")
    private Section section;

    // Constructeurs
    public Module() {}
    

    public Module(int idModule) {
		super();
		this.idModule = idModule;
	}


	public Module(String nomModule, float moyenne,Section section,int semestre,float coefModule) {
        this.nomModule = nomModule;
        this.moyenne = moyenne;
        this.section=section;
        this.semestre=semestre;
        this.coefModule=coefModule;
    }

    // Getters et Setters
    public int getIdModule() {
        return idModule;
    }

    public void setIdModule(int idModule) {
        this.idModule = idModule;
    }

    public String getNomModule() {
        return nomModule;
    }

    public void setNomModule(String nomModule) {
        this.nomModule = nomModule;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public List<Matiére> getListeMatieres() {
        return listeMatieres;
    }

    public void setListeMatieres(List<Matiére> listeMatieres) {
        this.listeMatieres = listeMatieres;
    }

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}


	public int getSemestre() {
		return semestre;
	}


	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}


	public float getCoefModule() {
		return coefModule;
	}


	public void setCoefModule(float coefModule) {
		this.coefModule = coefModule;
	}

   
}