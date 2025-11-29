package com.EvalTrack.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Etudiant extends Utilisateur {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long idEtudinat;
	@JsonProperty("cin")
	private Integer cin;
	private Integer niveau;
	@ManyToOne
	@JoinColumn(name = "sectionId")
	private Section section;
	
	@JsonIgnore
	@OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
	private List<Examen> exams;

	@ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "idRole")
    private Role role;
	
	public Etudiant(String nom, String email, String motDePasse, Integer cin, Integer niveau, Section section, List<Examen> exams, Role role) {
		super(nom != null ? nom : "", email != null ? email : "", motDePasse != null ? motDePasse : "");
		this.cin= cin !=null? cin:0;
		this.niveau = niveau!=null ? niveau :0;
		this.section = section !=null? section :null;
		this.exams = exams;
		this.role=role;
	}
	
	public Etudiant() {
		super();
		
	}
	

	public Etudiant( Integer cin) {
		super();
		this.cin = cin;
	}

	public Long getIdEtudinat() {
		return idEtudinat;
	}

	public void setIdEtudinat(Long idEtudinat) {
		this.idEtudinat = idEtudinat;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Etudiant( Long id) {
		super();
		this.idEtudinat = id;
	}

	public List<Examen> getExams() {
		return exams;
	}

	public void setExams(List<Examen> exams) {
		this.exams = exams;
	}

	public Long getId() {
		return idEtudinat;
	}
	public void setId(Long id) {
		this.idEtudinat = id;
	}
	public Integer getCin() {
		return cin;
	}
	public void setCin(Integer cin) {
		this.cin = cin;
	}
	public Integer getNiveau() {
		return niveau;
	}
	public void setNiveau(Integer niveau) {
		this.niveau = niveau;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	

}
