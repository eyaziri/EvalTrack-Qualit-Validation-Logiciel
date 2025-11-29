package com.EvalTrack.Entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Role {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long idRole;
	private String nomString ;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<Etudiant> etudiants;
	@JsonIgnore
	@OneToMany(mappedBy = "role")
	private List<Administrateur> administrateurs;
	public Role(Long idRole, String nomString) {
		super();
		this.idRole = idRole;
		this.nomString = nomString;
	}
	public Role(Long idRole) {
		super();
		this.idRole = idRole;
	}
	public Role() {
		super();
	}
	
	
	

}
