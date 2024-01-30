package com.example.be_study.service.user.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private int port;

    @Value("${mail.username}")
    private String userName;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.properties.mail.smtp.auth}")
    private boolean smtpAuth;

    @Value("${mail.properties.mail.smtp.starttls.enable}")
    private boolean starttlsEnable;

    @Value("${mail.properties.mail.smtp.starttls.required}")
    private boolean starttlsRequired;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setUsername(userName);
        javaMailSender.setPassword(password);
        javaMailSender.setJavaMailProperties(getMailproperties());
        javaMailSender.setDefaultEncoding("UTF-8");

        return javaMailSender;
    }

    private Properties getMailproperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", smtpAuth);
        properties.put("mail.smtp.starttls.enable", starttlsEnable);
        properties.put("mail.smtp.starttls.required", starttlsRequired);

        return properties;
    }
}
