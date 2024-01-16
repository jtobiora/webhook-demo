package com.swiftfingers.webhookdemo.config;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import java.util.Properties;

@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }

//    @Bean
//    public JavaMailSender getJavaMailSender() {
////        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
////        mailSender.setHost(env);
////        mailSender.setPort(587);
////
////        mailSender.setUsername("your@gmail.com");
////        mailSender.setPassword("copiedPassword");
////
////        Properties props = mailSender.getJavaMailProperties();
////        props.put("mail.transport.protocol", "smtp");
////        props.put("mail.smtp.auth", "true");
////        props.put("mail.smtp.starttls.enable", "true");
////        props.put("mail.debug", "true");
//
//        return null;
//    }
}
