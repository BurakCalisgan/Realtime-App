package com.realtime.api.realtimeapp.config;

import com.realtime.api.realtimeapp.util.props.SmtpProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class SmtpConfig {

    private final SmtpProperties smtpProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setProtocol(smtpProperties.getProtocol());
        mailSender.setHost(smtpProperties.getHost());
        mailSender.setPort(smtpProperties.getPort());
        mailSender.setUsername(smtpProperties.getUsername());
        mailSender.setPassword(smtpProperties.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", smtpProperties.getProperties().getProperty("mail.smtp.auth","true"));
        props.put("mail.smtp.starttls.enable", smtpProperties.getProperties().getProperty("mail.smtp.starttls.enable","true"));
        props.put("mail.smtp.ssl", smtpProperties.getProperties().getProperty("mail.smtp.ssl","smtp.gmail.com"));

        return mailSender;
    }
}
