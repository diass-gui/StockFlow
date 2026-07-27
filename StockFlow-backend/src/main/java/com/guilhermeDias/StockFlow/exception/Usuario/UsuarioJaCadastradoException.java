package com.guilhermeDias.StockFlow.exception.Usuario;

public class UsuarioJaCadastradoException extends RuntimeException {
    public UsuarioJaCadastradoException() { super("Já existe um usuário cadastrado com os dados informados."); }
    public UsuarioJaCadastradoException(String message) {
        super(message);
    }
}
