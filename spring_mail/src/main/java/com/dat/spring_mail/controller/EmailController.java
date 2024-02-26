package com.dat.spring_mail.controller;

import com.dat.spring_mail.dto.EmailDetail;
import com.dat.spring_mail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmailController {
    private final EmailService emailService;

    //Send email
    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@RequestBody EmailDetail emailDetail){
        return ResponseEntity.ok(emailService.sendSimpleEmail(emailDetail));
    }

    //Send email with attachment
    @PostMapping("/send/attachment")
    public ResponseEntity<String> sendEmailWithAttachment(@RequestBody EmailDetail emailDetailAttachment){
        return ResponseEntity.ok(emailService.sendEmailWithAttachment(emailDetailAttachment));
    }
}
