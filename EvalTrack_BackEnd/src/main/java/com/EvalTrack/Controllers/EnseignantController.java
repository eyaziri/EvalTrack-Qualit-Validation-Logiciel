package com.EvalTrack.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.EvalTrack.Entities.Enseignant;
import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Services.EnseignantService;

@RestController
@RequestMapping("/enseignant")
@CrossOrigin(origins = "http://localhost:8080")
public class EnseignantController {
 
	private EnseignantService enseignantService;

	@Autowired
	public EnseignantController(EnseignantService enseignantService) {
		super();
		this.enseignantService = enseignantService;
	}
	
	@PostMapping("/CreateEnseignant")
	@ResponseBody
	public ResponseEntity<String> createEnseignant(@RequestBody Enseignant es)
	{
		
		Enseignant createEnseignant = enseignantService.createEnseignant(es);
		if(createEnseignant!=null)
		{
			return ResponseEntity.ok("Enseignant créé avec succés");
			
		}else
		{
			 return ResponseEntity.status(400).body("Erreur lors de la création de Enseignant");
		}
	}
	@PostMapping("/LoginEnseignant")
	@ResponseBody
	public ResponseEntity<String> loginEnseignant (
	        @RequestBody Enseignant  enseignant ) {

	    String isAuthenticated = enseignantService.login(enseignant.getEmail(),enseignant.getMotDePasse());

	    if (isAuthenticated != null) {
	        return ResponseEntity.ok("Connexion réussie !");
	    } else {
	        return ResponseEntity.status(401).body("Email ou mot de passe incorrect.");
	    }
	}
	 @PutMapping("/{id}")
	 public ResponseEntity<Enseignant> updateEnseignant(@PathVariable Long id, @RequestBody Enseignant newData) {
		 
		 return ResponseEntity.ok(enseignantService.modifierEnseignant(id, newData)) ;
	 }
	 @GetMapping
	    public List<Enseignant> getAllEnseignant() {
	        return enseignantService.getAllEnseignants();
	    }
	 
	 @GetMapping("/{id}")
	    public Optional<Enseignant> getEnseignant(@PathVariable Long id) {
	        return enseignantService.getEtudiantById(id);
	    }
	
}
