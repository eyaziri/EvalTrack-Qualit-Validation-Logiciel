package com.EvalTrack.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.time.LocalDateTime;

@Entity
public class Reclamation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReclamation;
    
    private LocalDateTime dateCreation;

    @Enumerated(EnumType.STRING)
    private TypeReclamation type;  // Nouveau champ pour le type de réclamation

    @Enumerated(EnumType.STRING)
    private StatutReclamation statut;  // Nouveau champ pour le statut de la réclamation

    private String reponseAdmin;  // Nouveau champ pour la réponse de l'administrateur

    private LocalDateTime dateResolution;  // Nouveau champ pour la date de résolution

    private String matiereConcerne;  // Nouveau champ pour la matière concernée par la réclamation

    private String nomProf;  // Nouveau champ pour le nom du professeur concerné

  

    @ManyToOne
    @JoinColumn(name = "id_etudiant", referencedColumnName = "idEtudinat")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "id_admin", referencedColumnName = "id")
    private Administrateur administrateur;

    public Reclamation() {
        this.dateCreation = LocalDateTime.now();
        this.statut = StatutReclamation.EN_COURS;  // Valeur par défaut du statut
    }

    public Reclamation(Etudiant etudiant, Administrateur administrateur, TypeReclamation type, String matiereConcerne, String nomProf ) {
        this.etudiant = etudiant;
        this.administrateur = administrateur;
        this.type = type;
        this.dateCreation = LocalDateTime.now();
        this.statut = StatutReclamation.EN_COURS;  // Valeur par défaut du statut
        this.matiereConcerne = matiereConcerne;
        this.nomProf = nomProf;
       
    }

    // Getters and Setters
    public Long getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(Long idReclamation) {
        this.idReclamation = idReclamation;
    }

  

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public TypeReclamation getType() {
        return type;
    }

    public void setType(TypeReclamation type) {
        this.type = type;
    }

    public StatutReclamation getStatut() {
        return statut;
    }

    public void setStatut(StatutReclamation statut) {
        this.statut = statut;
    }

    public String getReponseAdmin() {
        return reponseAdmin;
    }

    public void setReponseAdmin(String reponseAdmin) {
        this.reponseAdmin = reponseAdmin;
    }

    public LocalDateTime getDateResolution() {
        return dateResolution;
    }

    public void setDateResolution(LocalDateTime dateResolution) {
        this.dateResolution = dateResolution;
    }

    public String getMatiereConcerne() {
        return matiereConcerne;
    }

    public void setMatiereConcerne(String matiereConcerne) {
        this.matiereConcerne = matiereConcerne;
    }

    public String getNomProf() {
        return nomProf;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }

   
    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }

	public void setId(long l) {
		this.idReclamation=l;
		
	}

	public Long getId() {
		
		return this.idReclamation;
	}
}
