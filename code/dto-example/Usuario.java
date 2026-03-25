package com.ejemplo.usuario.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

/**
 * Entidad Usuario - Modelo de persistencia
 * 
 * Nota: contiene passwordHash que NO se debe exponer en la API
 * Por eso necesitamos DTO para separar lo que se expone públicamente
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
    
    @Column(nullable = false)
    private String passwordHash;  // SENSIBLE - no exponer en API
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    /**
     * Constructor para crear usuario sin ID
     */
    public Usuario(String nombre, String email, String passwordHash) {
        this.nombre = nombre;
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
