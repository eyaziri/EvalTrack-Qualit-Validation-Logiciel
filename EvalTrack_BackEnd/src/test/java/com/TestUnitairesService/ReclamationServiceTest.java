package com.TestUnitairesService;

import com.EvalTrack.Entities.Reclamation;
import com.EvalTrack.Entities.StatutReclamation;
import com.EvalTrack.Entities.TypeReclamation;
import com.EvalTrack.Repositories.ReclamationRepository;
import com.EvalTrack.Services.ReclamationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReclamationServiceTest {

    @Mock
    private ReclamationRepository reclamationRepository;

    @InjectMocks
    private ReclamationService reclamationService;

    private Reclamation reclamation;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialisation de l'objet Reclamation avec des données factices
        reclamation = new Reclamation();
        reclamation.setIdReclamation(1L);
        reclamation.setType(TypeReclamation.NOTE_NON_AFFICHÉE);
        reclamation.setStatut(StatutReclamation.EN_COURS);
        reclamation.setDateCreation(LocalDateTime.now());
        reclamation.setNomProf("Professeur X");
        reclamation.setMatiereConcerne("Mathématiques");
       
    }

    @Test
    public void testAddReclamation() {
        when(reclamationRepository.save(any(Reclamation.class))).thenReturn(reclamation);

        Reclamation addedReclamation = reclamationService.addReclamation(reclamation);

        assertNotNull(addedReclamation);
        assertEquals(reclamation.getNomProf(), addedReclamation.getNomProf());
        assertEquals(reclamation.getType(), addedReclamation.getType());
        verify(reclamationRepository, times(1)).save(any(Reclamation.class));
    }

    @Test
    public void testUpdateReclamation() {
        // Préparer l'objet de réclamation à mettre à jour
        Reclamation updatedReclamation = new Reclamation();
        updatedReclamation.setIdReclamation(1L);
        updatedReclamation.setStatut(StatutReclamation.TRAITEE);
        updatedReclamation.setReponseAdmin("Réclamation résolue.");

        when(reclamationRepository.findById(1L)).thenReturn(Optional.of(reclamation));
        when(reclamationRepository.save(any(Reclamation.class))).thenReturn(updatedReclamation);

        Reclamation result = reclamationService.updateReclamation(1L, updatedReclamation);

        assertNotNull(result);
        assertEquals(StatutReclamation.TRAITEE, result.getStatut());
        assertEquals("Réclamation résolue.", result.getReponseAdmin());
        verify(reclamationRepository, times(1)).save(any(Reclamation.class));
    }

    @Test
    public void testUpdateReclamation_NotFound() {
        // Simuler le cas où la réclamation n'est pas trouvée
        Reclamation updatedReclamation = new Reclamation();
        updatedReclamation.setIdReclamation(1L);
        updatedReclamation.setStatut(StatutReclamation.TRAITEE);

        when(reclamationRepository.findById(1L)).thenReturn(Optional.empty());

        Reclamation result = reclamationService.updateReclamation(1L, updatedReclamation);

        assertNull(result);
        verify(reclamationRepository, never()).save(any(Reclamation.class));
    }

    @Test
    public void testDeleteReclamation() {
        // Simuler la suppression de la réclamation
        doNothing().when(reclamationRepository).deleteById(1L);

        reclamationService.deleteReclamation(1L);

        verify(reclamationRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetReclamationById() {
        when(reclamationRepository.findById(1L)).thenReturn(Optional.of(reclamation));

        Optional<Reclamation> foundReclamation = reclamationService.getReclamationById(1L);

        assertTrue(foundReclamation.isPresent());
        assertEquals(1L, foundReclamation.get().getIdReclamation());
    }

    @Test
    public void testGetReclamationById_NotFound() {
        when(reclamationRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Reclamation> foundReclamation = reclamationService.getReclamationById(1L);

        assertFalse(foundReclamation.isPresent());
    }
}
