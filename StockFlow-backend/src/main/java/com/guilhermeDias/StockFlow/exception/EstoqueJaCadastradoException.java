package com.guilhermeDias.StockFlow.exception;

public class EstoqueJaCadastradoException extends RuntimeException {
    public EstoqueJaCadastradoException() { super("Já existe um estoque com os dados informados."); }
    public EstoqueJaCadastradoException(String message) {
        super(message);
    }
}
