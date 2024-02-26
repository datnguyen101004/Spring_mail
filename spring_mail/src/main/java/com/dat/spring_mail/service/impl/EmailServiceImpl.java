package com.dat.spring_mail.service.impl;

import com.dat.spring_mail.dto.EmailDetail;
import com.dat.spring_mail.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    //Send email with simple email
    @Override
    public String sendSimpleEmail(EmailDetail emailDetail){
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setSubject(emailDetail.getSubject());
            simpleMailMessage.setTo(emailDetail.getRecipient());
            simpleMailMessage.setText(emailDetail.getMessage());
            javaMailSender.send(simpleMailMessage);

            return "Mail sent successfully";
        }
        catch (Exception e){
            return "Error";
        }
    }

    //Send email with attachment use MimeMessage
    @Override
    public String sendEmailWithAttachment(EmailDetail emailDetailAttachment){
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setSubject(emailDetailAttachment.getSubject());
            mimeMessageHelper.setTo(emailDetailAttachment.getRecipient());
            mimeMessageHelper.setText(emailDetailAttachment.getMessage());

            //Adding the attachment
            FileSystemResource fileSystemResource = new FileSystemResource(new File(emailDetailAttachment.getAttachment()));
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
            javaMailSender.send(mimeMessage);
            return "Mail sent successfully";

        } catch (MessagingException e) {
            return "Error";
        }
    }
}
