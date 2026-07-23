package com.ejemplo;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Aplicación Spring Boot - Ejemplo DTO con ModelMapper
 * 
 * Dependencias necesarias (pom.xml):
 * 
 * <dependency>
 *     <groupId>org.springframework.boot</groupId>
 *     <artifactId>spring-boot-starter-web</artifactId>
 * </dependency>
 * <dependency>
 *     <groupId>org.springframework.boot</groupId>
 *     <artifactId>spring-boot-starter-data-jpa</artifactId>
 * </dependency>
 * <dependency>
 *     <groupId>org.modelmapper</groupId>
 *     <artifactId>modelmapper</artifactId>
 *     <version>3.1.1</version>
 * </dependency>
 * <dependency>
 *     <groupId>com.h2database</groupId>
 *     <artifactId>h2</artifactId>
 * </dependency>
 * <dependency>
 *     <groupId>org.projectlombok</groupId>
 *     <artifactId>lombok</artifactId>
 * </dependency>
 */
@SpringBootApplication
public class Application {
    
    /**
     * Bean ModelMapper - disponible para inyectar en cualquier componente
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
