package com.EvalTrack.Entities;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Utilisateur {
	private String nom;
	@Column(unique = true, nullable = false)
	private String email;
	private String motDePasse;
	public Utilisateur( String nom, String email, String motDePasse) {
		this.nom = nom;
		this.email = email;
		this.motDePasse = motDePasse;
	}
	
	
	public Utilisateur() {
		super();
		
	}


	public Utilisateur(String email, String motDePasse) {
		super();
		this.email = email;
		this.motDePasse = motDePasse;
	}


	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMotDePasse() {
		return motDePasse;
	}
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
}
