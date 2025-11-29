package com.EvalTrack.Services;

import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Repositories.MatiereRepository;
import com.EvalTrack.Repositories.ModuleRepository;
import com.EvalTrack.Entities.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class MatiéreService {

    @Autowired
    private MatiereRepository matiereRepository;
    @Autowired
    private ModuleRepository  moduleRepository;

    // Ajouter une matière
    public Matiére ajouterMatiere(Matiére matiere) {
    	Matiére mat = new Matiére();
    	mat.setNom(matiere.getNom());
    	mat.setDescription(matiere.getDescription());
    	mat.setCoefficient(matiere.getCoefficient());
    	mat.setPonderation(matiere.getPonderation());
    	
    	if (matiere.getModule() != null) {
            com.EvalTrack.Entities.Module module = moduleRepository.findById(matiere.getModule().getIdModule()).orElse(null);
            mat.setModule(module);
        } else {
        	 mat.setModule(null);
        }
    	/*if (mat.getEnseignant() != null) {
           com.EvalTrack.Entities.Enseignant ens = moduleRepository.findById(matiere.getModule().getIdModule()).orElse(null);
            mat.setModule(module);
        } else {
        	 mat.setModule(null);
        }*/

        return matiereRepository.save(mat);
        
    }
    
    public Matiére addMatiereToModule(Matiére matiere, int moduleId) {
        com.EvalTrack.Entities.Module module = moduleRepository.findById(moduleId)
                .orElseThrow(() -> new RuntimeException("Module non trouvé"));
        matiere.setModule(module);
        return matiereRepository.save(matiere);
    }

    // Récupérer toutes les matières
    public List<Matiére> getAllMatieres() {
        return matiereRepository.findAll();
    }

    // Récupérer une matière par ID
    public Optional<Matiére> getMatiereById(Integer id) {
        return matiereRepository.findById(id);
    }

    // Mettre à jour une matière
    public Matiére updateMatiere(Integer id, Matiére matiereDetails) {
        Optional<Matiére> optionalMatiere = matiereRepository.findById(id);
        if (optionalMatiere.isPresent()) {
            Matiére matiere = optionalMatiere.get();
            
            // Conserve le module existant
            Module module = matiere.getModule();
            
            // Met à jour les autres champs
            matiere.setNom(matiereDetails.getNom());
            matiere.setDescription(matiereDetails.getDescription());
            matiere.setCoefficient(matiereDetails.getCoefficient());
            matiere.setMoyenne(matiereDetails.getMoyenne());
            matiere.setPonderation(matiereDetails.getPonderation());
            
            // Réassigne le module original
            matiere.setModule(module);
            
            return matiereRepository.save(matiere);
        } else {
            throw new RuntimeException("Matière non trouvée avec l'ID : " + id);
        }
    }

    // Supprimer une matière
    public void deleteMatiere(Integer id) {
        matiereRepository.deleteById(id);
    }
    
    public List<Matiére> getMatieresByTeachers (Integer id)
    {
            return matiereRepository.findByEnseignantEnseignantId(id);

    }
    public List<Matiére> getMatiereByIdModule(Integer idModule)
    {
    	return matiereRepository.findByModule_IdModule(idModule);
    }
}
