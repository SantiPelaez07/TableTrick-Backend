package com.riwi.table_trick.api.controllers;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/sistemareservas/v1/cliente")
                .allowedOrigins("http://127.0.0.1:5500")
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Especifica los métodos permitidos
                .allowedHeaders("*"); // Permite todas las cabeceras, puedes ajustarlo según tus necesidades
    }
}
