package com.TestUnitairesService;

import com.EvalTrack.Entities.Section;
import com.EvalTrack.Repositories.SectionRepository;
import com.EvalTrack.Services.SectionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SectionServiceTest {

    @Mock
    private SectionRepository sectionRepository;

    @InjectMocks
    private SectionService sectionService;

    private Section section;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation de l'objet Section avec des données factices
        section = new Section();
        section.setIdSection(1);
        section.setNomSection("Mathematics");
    }

    @Test
    public void testGetAllSections() {
        // Simuler la réponse du repository
        when(sectionRepository.findAll()).thenReturn(List.of(section));

        // Appel de la méthode
        var sections = sectionService.getAllSections();

        // Vérifier que la taille de la liste est correcte
        assertNotNull(sections);
        assertFalse(sections.isEmpty());
        assertEquals(1, sections.size());
    }

    @Test
    public void testGetSectionById() {
        // Simuler la réponse du repository
        when(sectionRepository.findById(1)).thenReturn(Optional.of(section));

        // Appel de la méthode
        Optional<Section> foundSection = sectionService.getSectionById(1);

        // Vérifier que la section est bien retrouvée
        assertTrue(foundSection.isPresent());
        assertEquals("Mathematics", foundSection.get().getNomSection());
    }

    @Test
    public void testGetSectionById_NotFound() {
        // Simuler le cas où la section n'est pas trouvée
        when(sectionRepository.findById(1)).thenReturn(Optional.empty());

        // Appel de la méthode
        Optional<Section> foundSection = sectionService.getSectionById(1);

        // Vérifier que la section n'a pas été trouvée
        assertFalse(foundSection.isPresent());
    }

    @Test
    public void testAddSection() {
        // Simuler la réponse du repository pour l'ajout
        when(sectionRepository.save(any(Section.class))).thenReturn(section);

        // Appel de la méthode
        Section addedSection = sectionService.addSection(section);

        // Vérifier que l'ajout fonctionne
        assertNotNull(addedSection);
        assertEquals("Mathematics", addedSection.getNomSection());
        verify(sectionRepository, times(1)).save(any(Section.class));
    }

    @Test
    public void testUpdateSection() {
        // Préparer une section mise à jour
        Section updatedSection = new Section();
        updatedSection.setNomSection("Physics");

        // Simuler la réponse du repository
        when(sectionRepository.findById(1)).thenReturn(Optional.of(section));
        when(sectionRepository.save(any(Section.class))).thenReturn(updatedSection);

        // Appel de la méthode
        Section result = sectionService.updateSection(1, updatedSection);

        // Vérifier que la section est mise à jour correctement
        assertNotNull(result);
        assertEquals("Physics", result.getNomSection());
        verify(sectionRepository, times(1)).save(any(Section.class));
    }

    @Test
    public void testUpdateSection_NotFound() {
        // Simuler le cas où la section n'existe pas
        Section updatedSection = new Section();
        updatedSection.setNomSection("Physics");

        when(sectionRepository.findById(1)).thenReturn(Optional.empty());

        // Appel de la méthode
        Section result = sectionService.updateSection(1, updatedSection);

        // Vérifier que la section n'est pas mise à jour
        assertNull(result);
        verify(sectionRepository, never()).save(any(Section.class));
    }

    @Test
    public void testUpdateSemestre() {
        // Préparer une section mise à jour pour le semestre
        Section updatedSection = new Section();
        updatedSection.setNomSection("Chemistry");

        // Simuler la réponse du repository
        when(sectionRepository.findById(1)).thenReturn(Optional.of(section));
        when(sectionRepository.save(any(Section.class))).thenReturn(updatedSection);

        // Appel de la méthode
        Section result = sectionService.updateSemestre(1, updatedSection);

        // Vérifier que le semestre est mis à jour correctement
        assertNotNull(result);
        assertEquals("Chemistry", result.getNomSection());
        verify(sectionRepository, times(1)).save(any(Section.class));
    }

    @Test
    public void testDeleteSection() {
        // Simuler la suppression de la section
        doNothing().when(sectionRepository).deleteById(1);

        // Appel de la méthode
        sectionService.deleteSection(1);

        // Vérifier que la méthode deleteById a été appelée
        verify(sectionRepository, times(1)).deleteById(1);
    }
}
