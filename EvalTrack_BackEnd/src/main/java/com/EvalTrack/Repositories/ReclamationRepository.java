package com.EvalTrack.Repositories;

import com.EvalTrack.Entities.Reclamation;
import com.EvalTrack.Entities.TypeReclamation;
import com.EvalTrack.Entities.StatutReclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long>  {

    // Trouver toutes les réclamations par statut
    List<Reclamation> findByStatut(StatutReclamation statut);

    // Trouver toutes les réclamations par type (utilisation de TypeReclamation)
    List<Reclamation> findByType(TypeReclamation type);

    // Trouver une réclamation par ID
    Optional<Reclamation> findById(Long idReclamation);

    // Trouver toutes les réclamations d'un étudiant spécifique
    List<Reclamation> findByEtudiant_Id(Long idEtudinat);
    
    List<Reclamation> findByAdministrateurId(Long idAdmin);

    // Trouver toutes les réclamations d'un administrateur spécifique
    List<Reclamation> findByAdministrateur_Id(Long idAdmin);

    // Trouver les réclamations envoyées après une certaine date
    List<Reclamation> findByDateCreationAfter(LocalDateTime date);

    // Trouver les réclamations qui ont été résolues, donc avec une date de résolution
    List<Reclamation> findByDateResolutionIsNotNull();
    
 // Trouver toutes les réclamations
    List<Reclamation> findAll();
    
 

}
