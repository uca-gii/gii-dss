package com.ejemplo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Aplicación Spring Boot - Ejemplo Repository Pattern
 * 
 * Dependencias necesarias (pom.xml):
 * 
 * <dependency>
 *     <groupId>org.springframework.boot</groupId>
 *     <artifactId>spring-boot-starter-data-jpa</artifactId>
 * </dependency>
 * <dependency>
 *     <groupId>com.h2database</groupId>
 *     <artifactId>h2</artifactId>
 *     <scope>runtime</scope>
 * </dependency>
 * <dependency>
 *     <groupId>org.projectlombok</groupId>
 *     <artifactId>lombok</artifactId>
 *     <optional>true</optional>
 * </dependency>
 * 
 * Configuración (application.properties):
 * 
 * spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
 * spring.jpa.hibernate.ddl-auto=create-drop
 * spring.h2.console.enabled=true
 */
@SpringBootApplication
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
