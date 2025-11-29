package com.TestUnitairesService;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import com.EvalTrack.Entities.Etudiant;
import com.EvalTrack.Entities.Examen;
import com.EvalTrack.Entities.Matiére;
import com.EvalTrack.Repositories.ExamenRepository;
import com.EvalTrack.Repositories.EtudiantRepository;
import com.EvalTrack.Repositories.MatiereRepository;
import com.EvalTrack.Services.ExamenService;

public class ExamenServiceTest {

    @Mock
    private ExamenRepository examenRepository;

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private MatiereRepository matiereRepository;

    @InjectMocks
    private ExamenService examenService;

    private Examen exam;
    private Etudiant etudiant;
    private Matiére matiere;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        etudiant = new Etudiant();
        etudiant.setId(1L);
        etudiant.setCin(123456);

        matiere = new Matiére();
        matiere.setMatiereId(1);
        matiere.setNom("Math");

        exam = new Examen();
        exam.setIdExam(1);
        exam.setTypeExam("Final");
        exam.setNotes(15.5d);
        exam.setLienCopie("link/to/copy");
        exam.setSession("Session 1");
        exam.setEtudiant(etudiant);
        exam.setMatiere(matiere);
    }

    @Test
    public void testSaveExam() {
        when(etudiantRepository.findByCin(123456)).thenReturn(Optional.of(etudiant));
        when(matiereRepository.findById(1)).thenReturn(Optional.of(matiere));
        when(examenRepository.save(any(Examen.class))).thenReturn(exam);

        Examen saved = examenService.saveExam(exam);
        assertNotNull(saved);
        assertEquals("Final", saved.getTypeExam());
        verify(examenRepository).save(any(Examen.class));
    }

    @Test
    public void testGetExamById() {
        when(examenRepository.findById(1)).thenReturn(Optional.of(exam));

        Examen found = examenService.getExamById(1);

        assertNotNull(found);
        assertEquals("Final", found.getTypeExam());
    }

    @Test
    public void testDeleteExam() {
        doNothing().when(examenRepository).deleteById(1);
        examenService.deleteExam(1);
        verify(examenRepository).deleteById(1);
    }

    @Test
    public void testUpdateExam() {
        when(examenRepository.findById(1)).thenReturn(Optional.of(exam));
        when(etudiantRepository.findById(1L)).thenReturn(Optional.of(etudiant));
        when(matiereRepository.findById(1)).thenReturn(Optional.of(matiere));
        when(examenRepository.save(any(Examen.class))).thenReturn(exam);

        Examen updated = new Examen();
        updated.setTypeExam("Partiel");
        updated.setNotes(18.0d);
        updated.setLienCopie("new/link");
        updated.setSession("Session 2");
        updated.setEtudiant(etudiant);
        updated.setMatiere(matiere);

        Examen result = examenService.updateExam(1, updated);

        assertNotNull(result);
        assertEquals("Partiel", result.getTypeExam());
        assertEquals(18.0f, result.getNotes());
    }

    @Test
    public void testFindByStudent() {
        when(examenRepository.findByEtudiantId(1)).thenReturn(List.of(exam));
        List<Examen> list = examenService.findByStudent(1);
        assertEquals(1, list.size());
    }

    @Test
    public void testFindBySubject() {
        when(examenRepository.findByMatiereId(1)).thenReturn(List.of(exam));
        List<Examen> list = examenService.findBySubject(1);
        assertEquals(1, list.size());
    }

    @Test
    public void testFindByType() {
        when(examenRepository.findByTypeExam("Final")).thenReturn(List.of(exam));
        List<Examen> list = examenService.findByType("Final");
        assertEquals(1, list.size());
    }
}
