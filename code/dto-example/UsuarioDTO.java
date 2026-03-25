package com.ejemplo.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * DTO (Data Transfer Object) para Usuario
 * 
 * Contiene solo los datos que se exponen a través de la API REST
 * NO contiene información sensible (contraseñas, datos internos)
 * 
 * Lombok genera: constructores, getters, setters, toString, equals, hashCode
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    
    private Long id;
    
    private String nombre;
    
    private String email;
    
    private LocalDateTime createdAt;
}
