package com.swiftfingers.webhookdemo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Global configurations for the app can go here
 *
 */
@Configuration
public class AppConfig {

    @Bean
    public RestTemplate restTemplate () {
        return new RestTemplate();
    }

}
