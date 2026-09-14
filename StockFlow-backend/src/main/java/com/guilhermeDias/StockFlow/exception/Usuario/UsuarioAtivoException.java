package com.guilhermeDias.StockFlow.exception.Usuario;

public class UsuarioAtivoException extends RuntimeException {
    public UsuarioAtivoException() { super("O usuário está ativo no sistema."); }
    public UsuarioAtivoException(String message) {
        super(message);
    }
}
