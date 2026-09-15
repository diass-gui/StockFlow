package com.guilhermeDias.StockFlow.exception.Usuario;

public class UsuarioDesativadoException extends RuntimeException {
    public UsuarioDesativadoException() { super("O usuário está desativado no sistema."); }
    public UsuarioDesativadoException(String message) {
        super(message);
    }
}
