package com.EvalTrack.Entities;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;



@Entity
public class Administrateur extends Utilisateur {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "idRole")
    private Role role;
	
	public Administrateur(Integer id, String nom, String email, String motDePasse,Role role) {
		super(nom != null ? nom : "", email != null ? email : "", motDePasse != null ? motDePasse : "");
	    this.id = id != null ? id : 0; 
	    this.role=role;
			
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Administrateur() {
		super();
		
	}
	

	

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	

}
