package com.TestUnitairesService;

import com.EvalTrack.Services.VerificationCodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VerificationCodeServiceTest {

    private VerificationCodeService verificationCodeService;

    @BeforeEach
    public void setUp() {
        verificationCodeService = new VerificationCodeService(); // Initialiser le service
    }

    @Test
    public void testStoreCode() {
        // Stocker un code de vérification pour un email
        String email = "test@example.com";
        String code = "123456";

        verificationCodeService.storeCode(email, code);

        // Vérifier que le code est bien stocké pour l'email
        assertTrue(verificationCodeService.verifyCode(email, code));
    }

    @Test
    public void testVerifyCode_Valid() {
        // Stocker un code pour un email
        String email = "test@example.com";
        String code = "123456";
        verificationCodeService.storeCode(email, code);

        // Vérifier que le code est correct
        assertTrue(verificationCodeService.verifyCode(email, code));
    }

    @Test
    public void testVerifyCode_Invalid() {
        // Stocker un code pour un email
        String email = "test@example.com";
        String correctCode = "123456";
        String incorrectCode = "654321";
        verificationCodeService.storeCode(email, correctCode);

        // Vérifier qu'un code incorrect retourne false
        assertFalse(verificationCodeService.verifyCode(email, incorrectCode));
    }

    @Test
    public void testRemoveCode() {
        // Stocker un code pour un email
        String email = "test@example.com";
        String code = "123456";
        verificationCodeService.storeCode(email, code);

        // Vérifier que le code existe avant la suppression
        assertTrue(verificationCodeService.verifyCode(email, code));

        // Supprimer le code
        verificationCodeService.removeCode(email);

        // Vérifier que le code est supprimé
        assertFalse(verificationCodeService.verifyCode(email, code));
    }

    @Test
    public void testRemoveCode_NotStored() {
        // Vérifier que la suppression fonctionne même si le code n'est pas stocké
        String email = "test@example.com";
        verificationCodeService.removeCode(email);

        // Vérifier qu'il n'y a pas d'exception et que la méthode fonctionne correctement
        assertFalse(verificationCodeService.verifyCode(email, "123456"));
    }
}
