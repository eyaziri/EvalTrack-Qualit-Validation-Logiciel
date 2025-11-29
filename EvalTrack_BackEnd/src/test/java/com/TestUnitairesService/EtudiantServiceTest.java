package com.TestUnitairesService;

import com.EvalTrack.Entities.*;
import com.EvalTrack.Repositories.*;
import com.EvalTrack.Security.JwtService;
import com.EvalTrack.Services.EtudiantService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class EtudiantServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(EtudiantServiceTest.class);

    @Mock
    private EtudiantRepository etudiantRepository;

    @Mock
    private SectionRepository sectionRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private ReclamationRepository reclamationRepository;

    @Mock
    private UserRepository administrateurRepository;

    @InjectMocks
    private EtudiantService etudiantService;

    private Etudiant etudiant;

    @BeforeEach
    public void setUp() {
        logger.info("Initialisation des tests pour EtudiantService");

        Section section = new Section();
        section.setIdSection(1);

        Role role = new Role();
        role.setIdRole(2L);

        etudiant = new Etudiant();
        etudiant.setEmail("etudiant@test.com");
        etudiant.setMotDePasse("rawPassword");
        etudiant.setSection(section);
        etudiant.setRole(role);
        etudiant.setNiveau(3);
    }

    @Test
    public void testCreateEtudiant_shouldSaveEtudiantWithEncodedPassword() {
        logger.info("Début du test 'testCreateEtudiant_shouldSaveEtudiantWithEncodedPassword'");

        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(sectionRepository.findById(1)).thenReturn(Optional.of(etudiant.getSection()));
        when(roleRepository.findById(2L)).thenReturn(Optional.of(etudiant.getRole()));
        when(etudiantRepository.save(any(Etudiant.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Etudiant saved = etudiantService.createEtudiant(etudiant);

        assertNotNull(saved);
        assertEquals("encodedPassword", saved.getMotDePasse());
        assertEquals(3, saved.getNiveau());

        logger.info("Fin du test 'testCreateEtudiant_shouldSaveEtudiantWithEncodedPassword' sans erreur");
    }

    @Test
    public void testLogin_shouldReturnTokenAndUserDetailsIfValidCredentials() {
        logger.info("Début du test 'testLogin_shouldReturnTokenAndUserDetailsIfValidCredentials'");

        Etudiant e = new Etudiant();
        e.setEmail("etudiant@test.com");
        e.setMotDePasse("encodedPassword");

        Role role = new Role();
        role.setIdRole(2L);
        e.setRole(role);
        e.setId(5L);

        when(etudiantRepository.findByEmail("etudiant@test.com")).thenReturn(Optional.of(e));
        when(passwordEncoder.matches("rawPassword", "encodedPassword")).thenReturn(true);
        when(jwtService.generateToken("etudiant@test.com")).thenReturn("mockedToken");

        Map<String, Object> result = etudiantService.login("etudiant@test.com", "rawPassword");

        assertNotNull(result);
        assertEquals("mockedToken", result.get("token"));
        assertEquals(2L, result.get("idRole"));
        assertEquals(5L, result.get("idUser"));

        logger.info("Fin du test 'testLogin_shouldReturnTokenAndUserDetailsIfValidCredentials' sans erreur");
    }
}
