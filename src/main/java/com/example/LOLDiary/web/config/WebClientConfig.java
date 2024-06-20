package com.example.LOLDiary.web.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ClientHttpConnector;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Value("${riot.api}")
    private String apiKey;
    private final Duration timeoutDuration = Duration.ofSeconds(30);

    @Bean
    public WebClient webClient(){
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, (int) timeoutDuration.toMillis())
                .doOnConnected(conn -> conn.addHandlerFirst(new ReadTimeoutHandler(Math.toIntExact(timeoutDuration.getSeconds()))));

        ClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
        return WebClient.builder()
                .clientConnector(connector)
                .defaultHeader("X-Riot-Token", apiKey)
                .build();
    }

}


