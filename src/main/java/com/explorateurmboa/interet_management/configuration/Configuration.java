package com.explorateurmboa.interet_management.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@org.springframework.context.annotation.Configuration
public class Configuration implements WebMvcConfigurer {
    private static Logger logger = LoggerFactory.getLogger(Configuration.class);

    @Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path uploads = Paths.get("interet-management/src/main/resources/static/uploads");
        String uploadDir = uploads.toAbsolutePath().toUri().toString();
        logger.info("Upload directory: " + uploadDir);
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(uploadDir);
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }
}
