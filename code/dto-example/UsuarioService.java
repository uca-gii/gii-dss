package com.ejemplo.usuario.service;

import com.ejemplo.usuario.dto.UsuarioDTO;
import com.ejemplo.usuario.entity.Usuario;
import com.ejemplo.usuario.mapper.UsuarioMapper;
import com.ejemplo.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

/**
 * Servicio de Usuario - Orquesta Repository, Mapper y lógica de negocio
 * 
 * Flujo:
 * Controller → Service → Repository → DTO ↔ Entity
 */
@Service
@Transactional
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }
    
    /**
     * Crea nuevo usuario a partir de DTO
     * Flujo: DTO → Entity → Repository.save → Entity → DTO
     */
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new EmailYaExisteException("Email ya existe");
        }
        
        // DTO → Entity
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        // (nota: en una app real, aquí hashearías la contraseña con BCrypt)
        
        // Entity → BD
        Usuario creado = usuarioRepository.save(usuario);
        
        // Entity → DTO
        return usuarioMapper.toDTO(creado);
    }
    
    /**
     * Obtiene usuario por ID
     * Entity → DTO
     */
    @Transactional(readOnly = true)
    public UsuarioDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        return usuarioMapper.toDTO(usuario);
    }
    
    /**
     * Lista todos usuarios
     * List<Entity> → List<DTO>
     */
    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarioMapper.toDTOList(usuarios);
    }
    
    /**
     * Actualiza usuario
     * DTO → Entity → BD → Entity → DTO
     */
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
        
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setEmail(usuarioDTO.getEmail());
        
        Usuario actualizado = usuarioRepository.save(usuario);
        return usuarioMapper.toDTO(actualizado);
    }
    
    /**
     * Elimina usuario
     */
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
