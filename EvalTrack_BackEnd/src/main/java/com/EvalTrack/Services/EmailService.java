package com.EvalTrack.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationCode(String toEmail, String code) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("🔐 Votre code de vérification");
        message.setText("Bonjour,\n\nVoici votre code de vérification : " + code + "\n\nCe code est valable pendant 10 minutes.\n\nL'équipe EvalTrack");
       

        mailSender.send(message);
    }

    public void sendNewPassword(String toEmail, String newPassword) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("🔑 Nouveau mot de passe temporaire");
        message.setText("Bonjour,\n\nVotre nouveau mot de passe temporaire est : " + newPassword + "\n\nVeuillez le modifier après votre connexion.\n\nL'équipe EvalTrack");
        message.setFrom("ton.email@gmail.com");

        mailSender.send(message);
    }
}

