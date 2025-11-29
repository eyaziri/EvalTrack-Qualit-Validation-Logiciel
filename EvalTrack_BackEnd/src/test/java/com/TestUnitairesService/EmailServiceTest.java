package com.TestUnitairesService;

import com.EvalTrack.Services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class EmailServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(EmailServiceTest.class);

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @Captor
    private ArgumentCaptor<SimpleMailMessage> messageCaptor;

    @BeforeEach
    public void setUp() {
        logger.info("Début de la configuration du test EmailService");
    }

    @Test
    void testSendVerificationCode_shouldSendEmailWithCode() {
        logger.info("Début du test 'testSendVerificationCode_shouldSendEmailWithCode'");

        String email = "test@example.com";
        String code = "123456";

        logger.debug("Appel de 'sendVerificationCode' avec email: " + email + " et code: " + code);
        emailService.sendVerificationCode(email, code);

        verify(mailSender).send(messageCaptor.capture());

        SimpleMailMessage sentMessage = messageCaptor.getValue();

        logger.debug("Vérification du contenu de l'e-mail envoyé...");
        assert sentMessage.getTo()[0].equals(email);
        assert sentMessage.getSubject().contains("code de vérification");
        assert sentMessage.getText().contains(code);

        logger.info("Fin du test 'testSendVerificationCode_shouldSendEmailWithCode' sans erreur");
    }

    @Test
    void testSendNewPassword_shouldSendEmailWithPassword() {
        logger.info("Début du test 'testSendNewPassword_shouldSendEmailWithPassword'");

        String email = "user@example.com";
        String newPassword = "temporary123";

        logger.debug("Appel de 'sendNewPassword' avec email: " + email + " et mot de passe: " + newPassword);
        emailService.sendNewPassword(email, newPassword);

        verify(mailSender).send(messageCaptor.capture());

        SimpleMailMessage sentMessage = messageCaptor.getValue();

        logger.debug("Vérification du contenu de l'e-mail envoyé...");
        assert sentMessage.getTo()[0].equals(email);
        assert sentMessage.getSubject().contains("mot de passe temporaire");
        assert sentMessage.getText().contains(newPassword);
        assert sentMessage.getFrom().equals("ton.email@gmail.com");

        logger.info("Fin du test 'testSendNewPassword_shouldSendEmailWithPassword' sans erreur");
    }
}
