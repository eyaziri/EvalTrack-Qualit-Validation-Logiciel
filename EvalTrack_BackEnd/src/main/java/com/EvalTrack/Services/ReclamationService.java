package com.EvalTrack.Services;

import com.EvalTrack.Entities.Reclamation;
import com.EvalTrack.Entities.StatutReclamation;
import com.EvalTrack.Entities.TypeReclamation;
import com.EvalTrack.Repositories.ReclamationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReclamationService {

    @Autowired
    private ReclamationRepository reclamationRepository;

    // Ajouter une nouvelle réclamation
    public Reclamation addReclamation(Reclamation reclamation) {
        return reclamationRepository.save(reclamation);  // Cela devrait être valide pour une instance de Reclamation
    }

    // Trouver une réclamation par ID
    public Optional<Reclamation> getReclamationById(Long idReclamation) {
        return reclamationRepository.findById(idReclamation);
    }

    // Trouver toutes les réclamations d'un étudiant
    public List<Reclamation> getReclamationsByEtudiant(Long idEtudinat) {
        return reclamationRepository.findByEtudiant_Id(idEtudinat);
    }
    
    public List<Reclamation> getReclamationsByAdmin(Long idAdmin) {
        return reclamationRepository.findByAdministrateurId(idAdmin);
    }

    // Trouver toutes les réclamations par statut
    public List<Reclamation> getReclamationsByStatut(StatutReclamation statut) {
        return reclamationRepository.findByStatut(statut);
    }

    // Trouver toutes les réclamations par type
    public List<Reclamation> getReclamationsByType(TypeReclamation type) {
        return reclamationRepository.findByType(type);
    }

    // Mettre à jour une réclamation (par exemple, changer son statut ou sa description)
    public Reclamation updateReclamation(Long idReclamation, Reclamation reclamationDetails) {
        Optional<Reclamation> existingReclamation = reclamationRepository.findById(idReclamation);
        if (existingReclamation.isPresent()) {
            Reclamation reclamation = existingReclamation.get();

            // Mise à jour des champs conservés
            reclamation.setType(reclamationDetails.getType());
            reclamation.setDateCreation(reclamationDetails.getDateCreation());
            reclamation.setStatut(reclamationDetails.getStatut());
            reclamation.setReponseAdmin(reclamationDetails.getReponseAdmin());
            reclamation.setDateResolution(reclamationDetails.getDateResolution());
            reclamation.setNomProf(reclamationDetails.getNomProf());
            reclamation.setMatiereConcerne(reclamationDetails.getMatiereConcerne());
            reclamation.setEtudiant(reclamationDetails.getEtudiant());
            reclamation.setAdministrateur(reclamationDetails.getAdministrateur());

            return reclamationRepository.save(reclamation);
        }
        return null; // ou lancer une exception NotFound
    }


    // Supprimer une réclamation par ID
    public void deleteReclamation(Long idReclamation) {
        reclamationRepository.deleteById(idReclamation);
    }

    // Trouver toutes les réclamations envoyées après une certaine date
    public List<Reclamation> getReclamationsAfterDate(LocalDateTime date) {
        return reclamationRepository.findByDateCreationAfter(date);
    }

    // Trouver toutes les réclamations résolues
    public List<Reclamation> getResolvedReclamations() {
        return reclamationRepository.findByDateResolutionIsNotNull();
    }
    
    public List<Reclamation> getAllReclamations() {
        return reclamationRepository.findAll();
    }
    
    public void changerStatut(Long idReclamation, String statut) {
        Reclamation r = reclamationRepository.findById(idReclamation)
            .orElseThrow(() -> new RuntimeException("Réclamation introuvable"));

        StatutReclamation nouveauStatut = StatutReclamation.valueOf(statut); // ⚠️ peut échouer ici
        r.setStatut(nouveauStatut);

        reclamationRepository.save(r);
    }



    
 


}
