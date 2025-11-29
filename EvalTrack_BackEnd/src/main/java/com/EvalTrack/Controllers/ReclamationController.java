package com.EvalTrack.Controllers;

import com.EvalTrack.Entities.Reclamation;
import com.EvalTrack.Entities.StatutReclamation;
import com.EvalTrack.Entities.TypeReclamation;
import com.EvalTrack.Repositories.ReclamationRepository;
import com.EvalTrack.Services.ReclamationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reclamations")
public class ReclamationController {

    @Autowired
    private ReclamationService reclamationService;
    @Autowired
    private ReclamationRepository reclamationRepository;

    @PostMapping
    public ResponseEntity<Reclamation> addReclamation(@RequestBody Reclamation reclamation) {
        Reclamation createdReclamation = reclamationService.addReclamation(reclamation);
        return ResponseEntity.ok(createdReclamation);
    }

    @GetMapping("/{idReclamation}")
    public ResponseEntity<Reclamation> getReclamationById(@PathVariable("idReclamation") Long idReclamation) {
        Optional<Reclamation> reclamation = reclamationService.getReclamationById(idReclamation);
        if (reclamation.isPresent()) {
            return ResponseEntity.ok(reclamation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/etudiant/{idEtudinat}")
    public ResponseEntity<List<Reclamation>> getReclamationsByEtudiant(@PathVariable("idEtudinat") Long idEtudinat) {
        List<Reclamation> reclamations = reclamationService.getReclamationsByEtudiant(idEtudinat);
        return ResponseEntity.ok(reclamations);
    }
    
    @GetMapping("/admin/{id}")
    public ResponseEntity<List<Reclamation>> getReclamationsByAdmin(@PathVariable("id") Long id) {
    	List<Reclamation> reclamations =  reclamationService.getReclamationsByAdmin(id);
    	return ResponseEntity.ok(reclamations);
    }



    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<Reclamation>> getReclamationsByStatut(@PathVariable StatutReclamation statut) {
        List<Reclamation> reclamations = reclamationService.getReclamationsByStatut(statut);
        return ResponseEntity.ok(reclamations);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Reclamation>> getReclamationsByType(@PathVariable TypeReclamation type) {
        List<Reclamation> reclamations = reclamationService.getReclamationsByType(type);
        return ResponseEntity.ok(reclamations);
    }

    @PutMapping("/{idReclamation}")
    public ResponseEntity<Reclamation> updateReclamation(@PathVariable Long idReclamation, @RequestBody Reclamation reclamationDetails) {
        Reclamation updatedReclamation = reclamationService.updateReclamation(idReclamation, reclamationDetails);
        if (updatedReclamation != null) {
            return ResponseEntity.ok(updatedReclamation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{idReclamation}")
    public ResponseEntity<Void> deleteReclamation(@PathVariable Long id) {
        reclamationService.deleteReclamation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dateAfter/{date}")
    public ResponseEntity<List<Reclamation>> getReclamationsAfterDate(
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {
        List<Reclamation> reclamations = reclamationService.getReclamationsAfterDate(date);
        return ResponseEntity.ok(reclamations);
    }


    @GetMapping("/resolved")
    public ResponseEntity<List<Reclamation>> getResolvedReclamations() {
        List<Reclamation> reclamations = reclamationService.getResolvedReclamations();
        return ResponseEntity.ok(reclamations);
    }
    
    @GetMapping
    public ResponseEntity<List<Reclamation>> getAllReclamations() {
        List<Reclamation> reclamations = reclamationService.getAllReclamations();
        return ResponseEntity.ok(reclamations);
    }
    
    @PutMapping("/{idReclamation}/changer-statut/{statut}")
    public ResponseEntity<?> changerStatut(
        @PathVariable("idReclamation") Long idReclamation,
        @PathVariable("statut") String statut) {
        try {
            System.out.println("ID reçu : " + idReclamation + ", Statut reçu : " + statut); // debug
            reclamationService.changerStatut(idReclamation, statut);
            return ResponseEntity.ok("Statut mis à jour");
        } catch (Exception e) {
            e.printStackTrace(); // pour voir l’erreur dans la console backend
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Erreur interne lors du changement de statut");
        }
    }


 


}
