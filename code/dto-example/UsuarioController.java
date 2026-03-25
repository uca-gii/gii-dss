package com.ejemplo.usuario.controller;

import com.ejemplo.usuario.dto.UsuarioDTO;
import com.ejemplo.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;

/**
 * Controlador REST - Expone API usando DTOs (no Entities!)
 * 
 * Ventaja: cambios en la BD/Entity NO afectan a clientes de API
 * Los clientes ven solo el contrato del DTO
 */
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;
    
    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    /**
     * GET /api/usuarios/{id}
     * Retorna: UsuarioDTO (sin passwordHash)
     */
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.obtenerPorId(id);
        return ResponseEntity.ok(usuarioDTO);
    }
    
    /**
     * GET /api/usuarios
     * Retorna: List<UsuarioDTO>
     */
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        List<UsuarioDTO> usuarios = usuarioService.listarTodos();
        return ResponseEntity.ok(usuarios);
    }
    
    /**
     * POST /api/usuarios
     * Acepta: UsuarioDTO
     * Retorna: UsuarioDTO creado (status 201 Created)
     */
    @PostMapping
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO creado = usuarioService.crearUsuario(usuarioDTO);
        URI location = URI.create("/api/usuarios/" + creado.getId());
        return ResponseEntity.created(location).body(creado);
    }
    
    /**
     * PUT /api/usuarios/{id}
     * Acepta: UsuarioDTO
     * Retorna: UsuarioDTO actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(
            @PathVariable Long id,
            @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO actualizado = usuarioService.actualizarUsuario(id, usuarioDTO);
        return ResponseEntity.ok(actualizado);
    }
    
    /**
     * DELETE /api/usuarios/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
