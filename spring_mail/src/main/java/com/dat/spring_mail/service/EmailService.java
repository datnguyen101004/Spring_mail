package com.dat.spring_mail.service;

import com.dat.spring_mail.dto.EmailDetail;

public interface EmailService {
    String sendSimpleEmail(EmailDetail emailDetail);
    String sendEmailWithAttachment(EmailDetail emailDetailAttachment);

}
