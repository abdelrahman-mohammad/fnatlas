package com.fnatlas.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class FortniteConfig {

    @Value("${FN_API_BASE_URL}")
    private String baseUrl;

    @Bean
    public WebClient fortniteWebClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("User-Agent", "FNAtlas/1.0")
                .build();
    }
}
