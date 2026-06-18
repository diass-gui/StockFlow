package com.guilhermeDias.StockFlow.exception;

public class EstoqueNaoEncontradoException extends RuntimeException {
    public EstoqueNaoEncontradoException(String message) {
        super(message);
    }
}
