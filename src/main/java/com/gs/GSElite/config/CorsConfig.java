package com.gs.GSElite.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "https://elite-giolla.web.app",
                        "https://elite-giolla.firebaseapp.com",
                        "http://localhost:4200"
                )
                .allowedMethods("GET");
    }
}
