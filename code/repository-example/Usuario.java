package com.ejemplo.usuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entidad Usuario - Modelo de persistencia con JPA
 * Anotaciones de Lombok para generar getters, setters, constructores
 */
@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length = 255)
    private String nombre;
    
    @Column(unique = true, nullable = false, length = 255)
    private String email;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Constructor para crear usuario sin ID (para nuevos registros)
     */
    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }
}
