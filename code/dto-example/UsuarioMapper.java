package com.ejemplo.usuario.mapper;

import com.ejemplo.usuario.dto.UsuarioDTO;
import com.ejemplo.usuario.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper usando ModelMapper
 * Convierte entre Entity (Usuario) y DTO (UsuarioDTO)
 * 
 * Ventajas de ModelMapper:
 * - Evita boilerplate de getters/setters manuales
 * - Mapeo automático de propiedades con mismo nombre
 * - Customizable para mapeos complejos
 */
@Component
public class UsuarioMapper {
    
    private final ModelMapper modelMapper;
    
    @Autowired
    public UsuarioMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    
    /**
     * Convierte Entity Usuario → DTO UsuarioDTO
     * (No copia passwordHash - queda excluido automáticamente)
     */
    public UsuarioDTO toDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }
    
    /**
     * Convierte DTO UsuarioDTO → Entity Usuario
     * (Solo mapea los campos del DTO; passwordHash quedará null)
     */
    public Usuario toEntity(UsuarioDTO usuarioDTO) {
        return modelMapper.map(usuarioDTO, Usuario.class);
    }
    
    /**
     * Convierte lista de Entities → lista de DTOs
     */
    public List<UsuarioDTO> toDTOList(List<Usuario> usuarios) {
        return usuarios.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }
    
    /**
     * Convierte lista de DTOs → lista de Entities
     */
    public List<Usuario> toEntityList(List<UsuarioDTO> usuarioDTOs) {
        return usuarioDTOs.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
