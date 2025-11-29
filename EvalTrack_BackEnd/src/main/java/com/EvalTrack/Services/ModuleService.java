package com.EvalTrack.Services;

import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Entities.Module;
import com.EvalTrack.DTOs.ModuleWithMatieresDTO;
import com.EvalTrack.Repositories.MatiereRepository;
import com.EvalTrack.Repositories.ModuleRepository;
import com.EvalTrack.Repositories.SectionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ModuleService {
	 @Autowired
	 private MatiereRepository matiereRepository;
    @Autowired
    private ModuleRepository moduleRepository;
    @Autowired
    private SectionRepository  sectionRepository;

    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    public Optional<Module> getModuleById(int id) {
        return moduleRepository.findById(id);
    }

    public Module addModule(Module module) {
    	Module mod = new Module();
    	mod.setMoyenne(module.getMoyenne());
    	mod.setNomModule(module.getNomModule());
    	mod.setSemestre(module.getSemestre());
    	mod.setCoefModule(module.getCoefModule());
    	if (module.getSection() != null) {
            com.EvalTrack.Entities.Section section = sectionRepository.findById(module.getSection().getIdSection()).orElse(null);
            mod.setSection(section);
        } else {
        	mod.setSection(null);
        }

        return moduleRepository.save(module);
    }
    
    
    public Module addModuleWithMatieres(Module module, List<Matiére> matieres) {
        // Save module first
        Module savedModule = moduleRepository.save(module);
        
        // Then save each matiere
        for (Matiére matiere : matieres) {
            try {
                matiere.setModule(savedModule);
                matiereRepository.save(matiere);
            } catch (Exception e) {
                // If saving matiere fails, delete the module to maintain consistency
                moduleRepository.delete(savedModule);
                throw new RuntimeException("Failed to save matiere: " + matiere.getNom() + 
                                       ". Error: " + e.getMessage());
            }
        }
        return savedModule;
    }
    
    public Module updateModule(int id, Module updatedModule) {
        return moduleRepository.findById(id)
            .map(module -> {
                // Copie sélective des champs
                module.setNomModule(updatedModule.getNomModule());
                module.setSemestre(updatedModule.getSemestre());
                module.setCoefModule(updatedModule.getCoefModule());
                return moduleRepository.save(module);
            })
            .orElseThrow(() -> new RuntimeException("Module non trouvé"));
    }
    public void deleteModule(int id) {
        moduleRepository.deleteById(id);
    }
    
    public List <Module> getlistModule(Long idSection,Long semestre)
    {
    	return moduleRepository.findBySection_SectionIdAndSemestre(idSection,semestre);
    }
    public List<ModuleWithMatieresDTO> getModulesAndMatieres(Long idSection, Long semestre) {
    
        List<Module> modules = moduleRepository.findBySection_SectionIdAndSemestre(idSection, semestre);
        List<ModuleWithMatieresDTO> result = new ArrayList<>();
    
        for (Module module : modules) {
        
            List<Matiére> matieres = matiereRepository.findByModule_IdModule(module.getIdModule());
            result.add(new ModuleWithMatieresDTO(module, matieres));
        }

        return result;
    }
    
    public List<Module> findBySectionAndSemestreWithMatieres(Long idSection, Long semestre) {
        return moduleRepository.findBySection_SectionIdAndSemestre(idSection, semestre);
    }
    

}