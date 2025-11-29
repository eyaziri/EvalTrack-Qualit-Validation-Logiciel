package com.EvalTrack.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
@Entity
public class Matiére {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer matiereId;
	
	@Column(nullable = false)
	private String nom;

	@Column(length = 500)
	private String description;
	
	@Column
	private float coefficient;
	
	@Column
	private float moyenne;
	
	@Column
	private String ponderation;
	@JsonIgnore
	@OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL)
	private List<Examen> exams;
	
	@ManyToOne
	@JoinColumn(name = "enseignant_id",nullable=true)
	private Enseignant  enseignant;
	
	@ManyToOne
	@JoinColumn(name = "idModule", nullable = false)
	private Module module;
	

	public Matiére(Integer matiereId, String nom, String description, float coefficient, float moyenne,
			String ponderation, List<Examen> exams, Enseignant enseignant, Module module) {
		super();
		this.matiereId = matiereId;
		this.nom = nom;
		this.description = description;
		this.coefficient = coefficient;
		this.moyenne = moyenne;
		this.ponderation = ponderation;
		this.exams = exams;
		this.enseignant = enseignant;
		this.module = module;
	}
	

	public Matiére() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Matiére(Integer matiereId) {
		super();
		this.matiereId = matiereId;
	}


	public float getCoefficient() {
		return coefficient;
	}

	public void setCoefficient(float coefficient) {
		this.coefficient = coefficient;
	}

	public float getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(float moyenne) {
		this.moyenne = moyenne;
	}

	public String getPonderation() {
		return ponderation;
	}

	public void setPonderation(String ponderation) {
		this.ponderation = ponderation;
	}




	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Enseignant getEnseignant() {
		return enseignant;
	}

	public void setEnseignant(Enseignant enseignant) {
		this.enseignant = enseignant;
	}

	
	public Matiére(Integer matiereId, List<Examen> exams) {
		super();
		this.matiereId = matiereId;
		this.exams = exams;
	}

	public List<Examen> getExams() {
		return exams;
	}



	public void setExams(List<Examen> exams) {
		this.exams = exams;
	}

	public Integer getMatiereId() {
		return matiereId;
	}

	public void setMatiereId(Integer matiereId) {
		this.matiereId = matiereId;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	
	
}
