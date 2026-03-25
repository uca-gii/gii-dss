package com.ejemplo.usuario.exception;

/**
 * Excepción: Usuario no encontrado
 */
public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

/**
 * Excepción: Email ya existe
 */
public class EmailYaExisteException extends RuntimeException {
    public EmailYaExisteException(String mensaje) {
        super(mensaje);
    }
}
