package com.ejemplo.usuario.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

public class EmailYaExisteException extends RuntimeException {
    public EmailYaExisteException(String mensaje) {
        super(mensaje);
    }
}
