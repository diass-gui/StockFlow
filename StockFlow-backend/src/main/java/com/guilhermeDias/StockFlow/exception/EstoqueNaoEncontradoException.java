package com.guilhermeDias.StockFlow.exception;

public class EstoqueNaoEncontradoException extends RuntimeException {
    public EstoqueNaoEncontradoException() { super("Estoque não encontrado."); }
    public EstoqueNaoEncontradoException(String message) {
        super(message);
    }
}
