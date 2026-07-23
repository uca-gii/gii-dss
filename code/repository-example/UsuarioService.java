package com.ejemplo.usuario.service;

import com.ejemplo.usuario.entity.Usuario;
import com.ejemplo.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Servicio de Usuario - Lógica de negocio
 * Utiliza el Repository para operaciones de persistencia
 */
@Service
@Transactional
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    
    /**
     * Crea un nuevo usuario
     * @throws EmailYaExisteException si el email ya existe
     */
    public Usuario crearUsuario(Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new EmailYaExisteException("El email " + usuario.getEmail() + " ya existe");
        }
        return usuarioRepository.save(usuario);
    }
    
    /**
     * Obtiene un usuario por ID
     * @throws UsuarioNoEncontradoException si no existe
     */
    @Transactional(readOnly = true)
    public Usuario obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con ID " + id + " no encontrado"));
    }
    
    /**
     * Obtiene un usuario por email
     */
    @Transactional(readOnly = true)
    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
            .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario con email " + email + " no encontrado"));
    }
    
    /**
     * Busca usuarios por nombre (búsqueda parcial, case-insensitive)
     */
    @Transactional(readOnly = true)
    public List<Usuario> buscarPorNombre(String nombre) {
        return usuarioRepository.findByNombreContainingIgnoreCase(nombre);
    }
    
    /**
     * Lista todos los usuarios
     */
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
    
    /**
     * Actualiza un usuario existente
     */
    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuario = obtenerPorId(id);
        usuario.setNombre(usuarioActualizado.getNombre());
        usuario.setEmail(usuarioActualizado.getEmail());
        return usuarioRepository.save(usuario);
    }
    
    /**
     * Elimina un usuario por ID
     */
    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
