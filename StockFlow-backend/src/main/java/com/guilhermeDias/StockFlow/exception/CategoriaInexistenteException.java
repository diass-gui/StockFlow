package com.guilhermeDias.StockFlow.exception;

public class CategoriaInexistenteException extends RuntimeException {
    public CategoriaInexistenteException() { super("Não existem produtos cadastrados com a categoria informada."); }
    public CategoriaInexistenteException(String message) {
        super(message);
    }
}
