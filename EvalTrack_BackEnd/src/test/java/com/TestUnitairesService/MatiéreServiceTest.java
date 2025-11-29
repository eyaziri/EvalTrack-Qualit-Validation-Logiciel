package com.TestUnitairesService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Repositories.MatiereRepository;
import com.EvalTrack.Repositories.ModuleRepository;
import com.EvalTrack.Services.MatiéreService;

public class MatiéreServiceTest {

    @Mock
    private MatiereRepository matiereRepository;

    @Mock
    private ModuleRepository moduleRepository;

    @InjectMocks
    private MatiéreService matiereService;

    private Matiére matiere;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        matiere = new Matiére();
        matiere.setMatiereId(1);
        matiere.setNom("Math");
        matiere.setDescription("Description");
        matiere.setCoefficient(3);
        matiere.setPonderation("0.4");
    }

    @Test
    public void testAjouterMatiere() {
        when(matiereRepository.save(any(Matiére.class))).thenReturn(matiere);

        Matiére saved = matiereService.ajouterMatiere(matiere);

        assertNotNull(saved);
        assertEquals("Math", saved.getNom());
        verify(matiereRepository).save(any(Matiére.class));
    }

    @Test
    public void testGetAllMatieres() {
        when(matiereRepository.findAll()).thenReturn(List.of(matiere));

        List<Matiére> matieres = matiereService.getAllMatieres();

        assertNotNull(matieres);
        assertEquals(1, matieres.size());
        assertEquals("Math", matieres.get(0).getNom());
    }

    @Test
    public void testGetMatiereById() {
        when(matiereRepository.findById(1)).thenReturn(Optional.of(matiere));

        Optional<Matiére> found = matiereService.getMatiereById(1);

        assertTrue(found.isPresent());
        assertEquals("Math", found.get().getNom());
    }

    @Test
    public void testUpdateMatiere() {
        when(matiereRepository.findById(1)).thenReturn(Optional.of(matiere));
        when(matiereRepository.save(any(Matiére.class))).thenReturn(matiere);

        matiere.setDescription("Updated Description");
        matiere.setCoefficient(4);

        Matiére updated = matiereService.updateMatiere(1, matiere);

        assertNotNull(updated);
        assertEquals("Updated Description", updated.getDescription());
        assertEquals(4, updated.getCoefficient());
    }

    @Test
    public void testDeleteMatiere() {
        doNothing().when(matiereRepository).deleteById(1);
        matiereService.deleteMatiere(1);
        verify(matiereRepository).deleteById(1);
    }

    @Test
    public void testGetMatieresByTeachers() {
        when(matiereRepository.findByEnseignantEnseignantId(1)).thenReturn(List.of(matiere));

        List<Matiére> matieres = matiereService.getMatieresByTeachers(1);

        assertNotNull(matieres);
        assertEquals(1, matieres.size());
    }

    @Test
    public void testGetMatiereByIdModule() {
        when(matiereRepository.findByModule_IdModule(1)).thenReturn(List.of(matiere));

        List<Matiére> matieres = matiereService.getMatiereByIdModule(1);

        assertNotNull(matieres);
        assertEquals(1, matieres.size());
    }
}
