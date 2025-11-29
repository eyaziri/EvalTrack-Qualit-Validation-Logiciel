package com.TestUnitairesService;

import com.EvalTrack.Entities.Enseignant;
import com.EvalTrack.Repositories.EnseignantRepository;
import com.EvalTrack.Security.JwtService;
import com.EvalTrack.Services.EnseignantService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class EnseignantServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(EnseignantServiceTest.class);

    @Mock
    private EnseignantRepository enseignantRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private EnseignantService enseignantService;

    @Captor
    private ArgumentCaptor<Enseignant> enseignantCaptor;

    private Enseignant enseignant;

    @BeforeEach
    public void setUp() {
        logger.info("Initialisation des objets de test pour EnseignantService");
        enseignant = new Enseignant();
        enseignant.setId(1);
        enseignant.setEmail("prof@example.com");
        enseignant.setMotDePasse("plainPassword");
    }

    @Test
    void testCreateEnseignant_shouldHashPasswordAndSave() {
        logger.info("Début du test 'testCreateEnseignant_shouldHashPasswordAndSave'");

        when(passwordEncoder.encode("plainPassword")).thenReturn("hashedPassword");
        when(enseignantRepository.save(any(Enseignant.class))).thenReturn(enseignant);

        Enseignant created = enseignantService.createEnseignant(enseignant);

        verify(passwordEncoder).encode("plainPassword");
        verify(enseignantRepository).save(enseignantCaptor.capture());

        Enseignant saved = enseignantCaptor.getValue();

        assertEquals("hashedPassword", saved.getMotDePasse());
        logger.info("Fin du test 'testCreateEnseignant_shouldHashPasswordAndSave' sans erreur");
    }

    @Test
    void testLogin_shouldReturnTokenOnValidCredentials() {
        logger.info("Début du test 'testLogin_shouldReturnTokenOnValidCredentials'");

        when(enseignantRepository.findByEmail("prof@example.com")).thenReturn(Optional.of(enseignant));
        when(passwordEncoder.matches("plainPassword", "plainPassword")).thenReturn(true);
        when(jwtService.generateToken("prof@example.com")).thenReturn("jwt-token");

        String token = enseignantService.login("prof@example.com", "plainPassword");

        assertEquals("jwt-token", token);
        logger.info("Fin du test 'testLogin_shouldReturnTokenOnValidCredentials'");
    }

    @Test
    void testLogin_shouldReturnNullOnInvalidPassword() {
        logger.info("Début du test 'testLogin_shouldReturnNullOnInvalidPassword'");

        when(enseignantRepository.findByEmail("prof@example.com")).thenReturn(Optional.of(enseignant));
        when(passwordEncoder.matches("wrongPassword", "plainPassword")).thenReturn(false);

        String result = enseignantService.login("prof@example.com", "wrongPassword");

        assertNull(result);
        logger.info("Fin du test 'testLogin_shouldReturnNullOnInvalidPassword'");
    }

    @Test
    void testModifierEnseignant_shouldUpdateAndEncryptNewPassword() {
        logger.info("Début du test 'testModifierEnseignant_shouldUpdateAndEncryptNewPassword'");

        Enseignant newData = new Enseignant();
        newData.setEmail("new@example.com");
        newData.setMotDePasse("newPassword");

        when(enseignantRepository.findById(1L)).thenReturn(Optional.of(enseignant));
        when(passwordEncoder.encode("newPassword")).thenReturn("encodedNewPass");
        when(enseignantRepository.save(any(Enseignant.class))).thenReturn(enseignant);

        Enseignant updated = enseignantService.modifierEnseignant(1L, newData);

        assertEquals("new@example.com", updated.getEmail());
        assertEquals("encodedNewPass", updated.getMotDePasse());

        logger.info("Fin du test 'testModifierEnseignant_shouldUpdateAndEncryptNewPassword'");
    }

    @Test
    void testGetAllEnseignants_shouldReturnList() {
        logger.info("Début du test 'testGetAllEnseignants_shouldReturnList'");

        List<Enseignant> list = List.of(enseignant);
        when(enseignantRepository.findAll()).thenReturn(list);

        List<Enseignant> result = enseignantService.getAllEnseignants();

        assertEquals(1, result.size());
        logger.info("Fin du test 'testGetAllEnseignants_shouldReturnList'");
    }

    @Test
    void testGetEtudiantById_shouldReturnOptional() {
        logger.info("Début du test 'testGetEtudiantById_shouldReturnOptional'");

        when(enseignantRepository.findById(1L)).thenReturn(Optional.of(enseignant));

        Optional<Enseignant> result = enseignantService.getEtudiantById(1L);

        assertTrue(result.isPresent());
        logger.info("Fin du test 'testGetEtudiantById_shouldReturnOptional'");
    }

    @Test
    void testModifierEnseignant_shouldThrowIfNotFound() {
        logger.info("Début du test 'testModifierEnseignant_shouldThrowIfNotFound'");

        when(enseignantRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            enseignantService.modifierEnseignant(99L, new Enseignant());
        });

        assertTrue(exception.getMessage().contains("Enseignant non trouvé"));
        logger.info("Fin du test 'testModifierEnseignant_shouldThrowIfNotFound'");
    }
}
