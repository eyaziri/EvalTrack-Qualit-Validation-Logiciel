package com.TestUnitairesService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Entities.Module;
import com.EvalTrack.Entities.ModuleWithMatieresDTO;
import com.EvalTrack.Repositories.MatiereRepository;
import com.EvalTrack.Repositories.ModuleRepository;
import com.EvalTrack.Repositories.SectionRepository;
import com.EvalTrack.Services.ModuleService;

public class ModuleServiceTest {

    @Mock
    private MatiereRepository matiereRepository;

    @Mock
    private ModuleRepository moduleRepository;

    @Mock
    private SectionRepository sectionRepository;

    @InjectMocks
    private ModuleService moduleService;

    private Module module;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        module = new Module();
        module.setIdModule(1);
        module.setNomModule("Mathematics");
        module.setSemestre(1);
        module.setCoefModule(3);
    }

    @Test
    public void testGetAllModules() {
        when(moduleRepository.findAll()).thenReturn(List.of(module));

        List<Module> modules = moduleService.getAllModules();

        assertNotNull(modules);
        assertEquals(1, modules.size());
        assertEquals("Mathematics", modules.get(0).getNomModule());
    }

    @Test
    public void testGetModuleById() {
        when(moduleRepository.findById(1)).thenReturn(Optional.of(module));

        Optional<Module> found = moduleService.getModuleById(1);

        assertTrue(found.isPresent());
        assertEquals("Mathematics", found.get().getNomModule());
    }

    @Test
    public void testAddModule() {
        // Création d'une section factice pour le module (si nécessaire)
        com.EvalTrack.Entities.Section section = new com.EvalTrack.Entities.Section();
        section.setIdSection(1);

        // Affectation d'une section au module (si nécessaire)
        module.setSection(section);

        // Mock des appels aux repositories
        when(sectionRepository.findById(module.getSection().getIdSection())).thenReturn(Optional.of(section)); // Maintenant, section existe
        when(moduleRepository.save(any(Module.class))).thenReturn(module);

        // Appel du service
        Module added = moduleService.addModule(module);

        // Vérification des résultats
        assertNotNull(added);
        assertEquals("Mathematics", added.getNomModule());
        verify(moduleRepository).save(any(Module.class));
    }


    @Test
    public void testUpdateModule() {
        when(moduleRepository.findById(1)).thenReturn(Optional.of(module));
        when(moduleRepository.save(any(Module.class))).thenReturn(module);

        module.setNomModule("Updated Mathematics");
        Module updated = moduleService.updateModule(1, module);

        assertNotNull(updated);
        assertEquals("Updated Mathematics", updated.getNomModule());
        verify(moduleRepository).save(any(Module.class));
    }

    @Test
    public void testDeleteModule() {
        doNothing().when(moduleRepository).deleteById(1);
        moduleService.deleteModule(1);
        verify(moduleRepository).deleteById(1);
    }

    @Test
    public void testGetListModule() {
        when(moduleRepository.findBySection_SectionIdAndSemestre(1L, 1L)).thenReturn(List.of(module));

        List<Module> modules = moduleService.getlistModule(1L, 1L);

        assertNotNull(modules);
        assertEquals(1, modules.size());
        assertEquals("Mathematics", modules.get(0).getNomModule());
    }

    @Test
    public void testGetModulesAndMatieres() {
        when(moduleRepository.findBySection_SectionIdAndSemestre(1L, 1L)).thenReturn(List.of(module));
        when(matiereRepository.findByModule_IdModule(module.getIdModule())).thenReturn(List.of(new Matiére()));

        List<com.EvalTrack.DTOs.ModuleWithMatieresDTO> modulesWithMatieres = moduleService.getModulesAndMatieres(1L, 1L);

        assertNotNull(modulesWithMatieres);
        assertEquals(1, modulesWithMatieres.size());
        assertEquals("Mathematics", modulesWithMatieres.get(0).getModule().getNomModule());
        assertEquals(1, modulesWithMatieres.get(0).getMatieres().size());
    }
}
