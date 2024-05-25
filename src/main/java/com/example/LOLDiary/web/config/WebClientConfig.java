package com.example.LOLDiary.web.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${riot.api}")
    private String apiKey;

    @Bean
    public WebClient webClient(){
        return WebClient.builder()
                .defaultHeader("X-Riot-Token", apiKey)
                .build();
    }

}


