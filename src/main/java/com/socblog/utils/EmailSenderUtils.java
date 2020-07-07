package com.socblog.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application-email.properties")
public class EmailSenderUtils{
    @Value("${spring.mail.username}")
    private String username;

    @Value("${spring.mail.password}")
    private String password;

    private JavaMailSender emailSender;
    @Autowired
    public EmailSenderUtils(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    public EmailSenderUtils(String username, String password){
        this.username = username;
        this.password = password;
    }
    @Async
    public void sendEmail(String to, String title, String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("noreply@baeldung.com");
        message.setTo(to);
        message.setSubject(title);
        message.setText(text);
        emailSender.send(message);
    }



}
