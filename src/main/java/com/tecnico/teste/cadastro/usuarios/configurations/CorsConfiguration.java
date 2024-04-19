package com.tecnico.teste.cadastro.usuarios.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration  implements WebMvcConfigurer {

    @Value("${clients.cors.allowed.origins}")
    private String[] origins;
    @Value("${clients.cors.allowed.headers}")
    private String[] headers;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(origins)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders(headers);
    }
}
