package com.ejemplo.usuario.repository;

import com.ejemplo.usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Repository para Usuario - Interfaz Spring Data JPA
 * 
 * Spring genera automáticamente la implementación basándose en:
 * - La interfaz JpaRepository<Entity, ID>
 * - Los nombres de métodos (query derivation)
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    /**
     * Query derivada: busca usuario por email
     * Spring genera: SELECT * FROM usuarios WHERE email = ?
     */
    Optional<Usuario> findByEmail(String email);
    
    /**
     * Query derivada: busca usuarios contenerlo nombre (case-insensitive)
     * Spring genera: SELECT * FROM usuarios WHERE LOWER(nombre) LIKE LOWER(?)
     */
    List<Usuario> findByNombreContainingIgnoreCase(String nombre);
    
    /**
     * Query derivada: busca usuarios creados después de una fecha
     * Spring genera: SELECT * FROM usuarios WHERE createdAt > ?
     */
    List<Usuario> findByCreatedAtAfter(LocalDateTime fecha);
    
    /**
     * Query derivada: verifica si existe usuario con email
     * Spring genera: SELECT COUNT(*) FROM usuarios WHERE email = ?
     */
    boolean existsByEmail(String email);
    
    /**
     * Query derivada: cuenta usuarios con nombre que comienza por...
     * Spring genera: SELECT COUNT(*) FROM usuarios WHERE nombre LIKE ?
     */
    long countByNombreStartingWith(String prefix);
}
