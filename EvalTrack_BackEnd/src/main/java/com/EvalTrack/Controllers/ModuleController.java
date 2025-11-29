package com.EvalTrack.Controllers;

import com.EvalTrack.Entities.Module;
import com.EvalTrack.Repositories.SectionRepository;
import com.EvalTrack.Services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import com.EvalTrack.DTOs.ModuleWithMatieresDTO; 
@RestController
@RequestMapping("/modules")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    @Autowired
    private SectionRepository sectionRepository ;

    @GetMapping
    public List<Module> getAllModules() {
        return moduleService.getAllModules();
    }

    @GetMapping("/{id}")
    public Optional<Module> getModuleById(@PathVariable int id) {
        return moduleService.getModuleById(id);
    }

    @PostMapping
    public Module createModule(@RequestBody Module module) {
        return moduleService.addModule(module);
    }
    
    @PostMapping("/with-matieres")
    public ResponseEntity<?> createModuleWithMatieres(@RequestBody ModuleWithMatieresDTO dto) {
        try {
            // Verify section exists first
            if (dto.getModule().getSection() == null || 
                !sectionRepository.existsById(dto.getModule().getSection().getIdSection())) {
                return ResponseEntity.badRequest().body("Section does not exist");
            }

            Module savedModule = moduleService.addModuleWithMatieres(dto.getModule(), dto.getMatieres());
            return ResponseEntity.ok(savedModule);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error creating module: " + e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateModule(
        @PathVariable("id") int id, 
        @RequestBody Module updatedModule) {
        
        try {
            Module result = moduleService.updateModule(id, updatedModule);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Erreur lors de la mise à jour: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public void deleteModule(@PathVariable("id") int id) {
        moduleService.deleteModule(id);
    }
    @GetMapping("/by-section/{idSection}/{semestre}")
    public List<Module> getModulesBySectionAndSemestre(
            @PathVariable("idSection") Long idSection, 
            @PathVariable("semestre") Long semestre) {
        return moduleService.getlistModule(idSection, semestre);
    }
    @GetMapping("/matieres/{idSection}/{semestre}")
    public List<ModuleWithMatieresDTO> getMatieres(
            @PathVariable("idSection") Long idSection, 
            @PathVariable("semestre") Long semestre) {
        return moduleService.getModulesAndMatieres(idSection, semestre);
    }
    
    @GetMapping("/section/{idSection}/semestre/{semestre}")
    public ResponseEntity<List<Module>> getModulesWithMatieres(
            @PathVariable Long idSection,
            @PathVariable Long semestre) {
        List<Module> modules = moduleService.findBySectionAndSemestreWithMatieres(idSection, semestre);
        return ResponseEntity.ok(modules);
    }

}