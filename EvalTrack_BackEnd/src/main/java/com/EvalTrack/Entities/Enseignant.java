/**
 * 
 */
package com.EvalTrack.Entities;

/**
 * @author eyazi
 *
 */
import java.util.List;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
public class Enseignant extends Utilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer enseignantId;

	@OneToMany(mappedBy = "enseignant", cascade = CascadeType.ALL)
	private List<Matiére> matieres;

	public Enseignant(String nom, String email, String motDePasse, Integer enseignantId, List<Matiére> matieres) {
		super(nom, email, motDePasse);
		this.enseignantId = enseignantId;
		this.matieres = matieres;
	}

	public Enseignant() {
		super();
	}

	public Enseignant(Integer enseignantId) {
		super();
		this.enseignantId = enseignantId;
	}

	public Integer getEnseignantId() {
		return enseignantId;
	}

	public void setEnseignantId(Integer enseignantId) {
		this.enseignantId = enseignantId;
	}

	public List<Matiére> getMatieres() {
		return matieres;
	}

	public void setMatieres(List<Matiére> matieres) {
		this.matieres = matieres;
	}

	public void setId(Integer l) {
		this.enseignantId=l;
	}
	

}
