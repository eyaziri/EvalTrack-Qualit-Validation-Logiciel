package com.EvalTrack.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Repositories.MatiereRepository;
import com.EvalTrack.Services.MatiéreService;

@RestController
@RequestMapping("/matieres")
public class MatiereController {

    @Autowired
    private MatiéreService matiereservice;

    // Récupérer toutes les matières
    @GetMapping
    public List<Matiére> getAllMatieres() {
        return matiereservice.getAllMatieres();
    }

    // Récupérer une matière par ID
    @GetMapping("/{id}")
    public Optional<Matiére> getMatiereById(@PathVariable("id") Integer id) {
        return matiereservice.getMatiereById(id);
    }

    // Ajouter une nouvelle matière
    @PostMapping
    public Matiére createMatiere(@RequestBody Matiére matiere) {
      return  matiereservice.ajouterMatiere(matiere);
    }
    
    @PostMapping("/for-module/{moduleId}")
    public Matiére addMatiereToModule(@PathVariable("moduleId") int moduleId, @RequestBody Matiére matiere) {
    	 if (matiere.getNom() == null || matiere.getNom().isEmpty()) {
    	        throw new IllegalArgumentException("Le nom de la matière est obligatoire");
    	    }
    	return matiereservice.addMatiereToModule(matiere, moduleId);
    }

    // Modifier une matière existante
    @PutMapping("/{id}")
    public Matiére updateMatiere(@PathVariable("id") Integer id, @RequestBody Matiére matiereDetails) {
        return matiereservice.updateMatiere(id, matiereDetails);
    }

    // Supprimer une matière
    @DeleteMapping("/{id}")
    public void deleteMatiere(@PathVariable("id") Integer id) {
        matiereservice.deleteMatiere(id);
    }
    
    @GetMapping("/module/{idModule}")
    public List<Matiére> getMatiereByIdModule(@PathVariable("idModule") Integer idModule) {
        return  matiereservice.getMatiereByIdModule(idModule);
    }
    

    // 🔹 Récupérer les matières d'un enseignant spécifique
    @GetMapping("/enseignant/{enseignantId}")
    public ResponseEntity<List<Matiére>> getMatieresByEnseignant(@PathVariable Integer enseignantId) {
        List<Matiére> matieres = matiereservice.getMatieresByTeachers(enseignantId);
        if (matieres.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matieres);
    }
}
