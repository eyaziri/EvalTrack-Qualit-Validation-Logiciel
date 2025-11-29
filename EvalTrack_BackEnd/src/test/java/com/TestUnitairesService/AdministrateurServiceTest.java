package com.TestUnitairesService;

import com.EvalTrack.Entities.Administrateur;
import com.EvalTrack.Entities.Reclamation;
import com.EvalTrack.Entities.Role;
import com.EvalTrack.Entities.StatutReclamation;
import com.EvalTrack.Repositories.ReclamationRepository;
import com.EvalTrack.Repositories.RoleRepository;
import com.EvalTrack.Repositories.UserRepository;
import com.EvalTrack.Security.JwtService;
import com.EvalTrack.Services.AdministrateurService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class AdministrateurServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(AdministrateurServiceTest.class);

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private JwtService jwtService;

    @Mock
    private ReclamationRepository reclamationRepository;

    @InjectMocks
    private AdministrateurService administrateurService;

    private Administrateur admin;

    @BeforeEach
    public void setUp() {
        admin = new Administrateur();
        admin.setId(1);
        admin.setEmail("admin@example.com");
        admin.setMotDePasse("plainPassword");

        Role role = new Role();
        role.setIdRole(2L);
        admin.setRole(role);

        logger.info("Début de la configuration pour le test de l'Administrateur avec email : " + admin.getEmail());
    }

    @Test
    void testCreateAdministrateur_shouldHashPasswordAndSave() {
        logger.info("Début du test 'testCreateAdministrateur_shouldHashPasswordAndSave'");
        String hashed = "hashedPassword";

        when(passwordEncoder.encode(anyString())).thenReturn(hashed);
        when(roleRepository.findById(anyLong())).thenReturn(Optional.of(admin.getRole()));
        when(userRepository.save(any(Administrateur.class))).thenAnswer(inv -> inv.getArgument(0));

        logger.debug("Lancement de la méthode 'createAdministrateur'...");
        Administrateur saved = administrateurService.createAdministrateur(admin);
        logger.debug("Administrateur sauvegardé : " + saved.getEmail());

        assertNotNull(saved);
        assertEquals(hashed, saved.getMotDePasse());
        assertEquals(admin.getRole(), saved.getRole());
        verify(userRepository).save(saved);

        logger.info("Fin du test 'testCreateAdministrateur_shouldHashPasswordAndSave' sans erreur");
    }

    @Test
    void testLogin_shouldReturnTokenAndIds_whenValidCredentials() {
        logger.info("Début du test 'testLogin_shouldReturnTokenAndIds_whenValidCredentials'");
        String token = "jwt-token";
        String encodedPass = "encodedPass";

        admin.setMotDePasse(encodedPass);

        when(userRepository.findByEmail(admin.getEmail())).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches("plainPassword", encodedPass)).thenReturn(true);
        when(jwtService.generateToken(admin.getEmail())).thenReturn(token);

        logger.debug("Lancement de la méthode 'login'...");
        Map<String, Object> result = administrateurService.login(admin.getEmail(), "plainPassword");
        logger.debug("Résultat du login : " + result);

        assertNotNull(result);
        assertEquals(token, result.get("token"));
        assertEquals(admin.getRole().getIdRole(), result.get("idRole"));
        assertEquals(admin.getId(), result.get("idUser"));

        logger.info("Fin du test 'testLogin_shouldReturnTokenAndIds_whenValidCredentials' sans erreur");
    }

    @Test
    void testGetAdminByEmail_shouldReturnAdmin() {
        logger.info("Début du test 'testGetAdminByEmail_shouldReturnAdmin'");

        when(userRepository.findByEmail("admin@example.com")).thenReturn(Optional.of(admin));
        Optional<Administrateur> found = administrateurService.getAdminByEmail("admin@example.com");

        logger.debug("Administrateur trouvé : " + (found.isPresent() ? found.get().getEmail() : "non trouvé"));

        assertTrue(found.isPresent());
        assertEquals(admin.getEmail(), found.get().getEmail());

        logger.info("Fin du test 'testGetAdminByEmail_shouldReturnAdmin' sans erreur");
    }

    @Test
    void testUpdatePassword_shouldGenerateAndSaveNewPassword() {
        logger.info("Début du test 'testUpdatePassword_shouldGenerateAndSaveNewPassword'");

        when(userRepository.findByEmail(admin.getEmail())).thenReturn(Optional.of(admin));
        when(passwordEncoder.encode(anyString())).thenReturn("newEncodedPassword");

        logger.debug("Lancement de la méthode 'updatePassword'...");
        String result = administrateurService.updatePassword(admin.getEmail());
        logger.debug("Mot de passe mis à jour pour l'admin : " + admin.getEmail());

        assertNotNull(result);
        verify(userRepository).save(admin);

        logger.info("Fin du test 'testUpdatePassword_shouldGenerateAndSaveNewPassword' sans erreur");
    }

    @Test
    void testUpdatePassword_shouldThrowException_whenOldPasswordIncorrect() {
        logger.info("Début du test 'testUpdatePassword_shouldThrowException_whenOldPasswordIncorrect'");

        when(userRepository.findByEmail(admin.getEmail())).thenReturn(Optional.of(admin));
        when(passwordEncoder.matches("wrongOld", admin.getMotDePasse())).thenReturn(false);

        logger.error("Ancien mot de passe incorrect pour l'admin : " + admin.getEmail());

        assertThrows(IllegalArgumentException.class, () -> {
            administrateurService.updatePassword("newPass", admin.getEmail(), "wrongOld");
        });

        logger.info("Fin du test 'testUpdatePassword_shouldThrowException_whenOldPasswordIncorrect' avec exception attendue");
    }

    @Test
    void testResolveReclamation_shouldUpdateFields() {
        logger.info("Début du test 'testResolveReclamation_shouldUpdateFields'");

        Reclamation reclamation = new Reclamation();
        reclamation.setId(1L);
        reclamation.setStatut(StatutReclamation.EN_COURS);

        when(reclamationRepository.findById(1L)).thenReturn(Optional.of(reclamation));
        when(reclamationRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        logger.debug("Lancement de la méthode 'resolveReclamation'...");
        Reclamation updated = administrateurService.resolveReclamation(1L, "Réponse de l'admin");
        logger.debug("Réclamation mise à jour : " + updated.getId());

        assertEquals(StatutReclamation.TRAITEE, updated.getStatut());
        assertEquals("Réponse de l'admin", updated.getReponseAdmin());
        assertNotNull(updated.getDateResolution());

        logger.info("Fin du test 'testResolveReclamation_shouldUpdateFields' sans erreur");
    }

    @Test
    void testGetReclamationsByStatut_shouldReturnList() {
        logger.info("Début du test 'testGetReclamationsByStatut_shouldReturnList'");

        List<Reclamation> list = Arrays.asList(new Reclamation(), new Reclamation());
        when(reclamationRepository.findByStatut(StatutReclamation.EN_COURS)).thenReturn(list);

        logger.debug("Lancement de la méthode 'getReclamationsByStatut'...");
        List<Reclamation> result = administrateurService.getReclamationsByStatut(StatutReclamation.EN_COURS);

        logger.debug("Nombre de réclamations récupérées : " + result.size());

        assertEquals(2, result.size());

        logger.info("Fin du test 'testGetReclamationsByStatut_shouldReturnList' sans erreur");
    }

    @Test
    void testChangeStatutReclamation_shouldUpdateStatut() {
        logger.info("Début du test 'testChangeStatutReclamation_shouldUpdateStatut'");

        Reclamation reclamation = new Reclamation();
        reclamation.setStatut(StatutReclamation.EN_COURS);

        when(reclamationRepository.findById(1L)).thenReturn(Optional.of(reclamation));
        when(reclamationRepository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        logger.debug("Lancement de la méthode 'changeStatutReclamation'...");
        Reclamation updated = administrateurService.changeStatutReclamation(1L, StatutReclamation.EN_COURS);
        logger.debug("Statut de la réclamation modifié : " + updated.getStatut());

        assertEquals(StatutReclamation.EN_COURS, updated.getStatut());

        logger.info("Fin du test 'testChangeStatutReclamation_shouldUpdateStatut' sans erreur");
    }
}
