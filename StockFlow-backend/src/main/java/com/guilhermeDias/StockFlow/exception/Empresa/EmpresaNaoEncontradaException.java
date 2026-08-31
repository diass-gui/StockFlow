package com.guilhermeDias.StockFlow.exception.Empresa;

public class EmpresaNaoEncontradaException extends RuntimeException {
    public EmpresaNaoEncontradaException() { super("Empresa não encontrada."); }
    public EmpresaNaoEncontradaException(String message) {
        super(message);
    }
}
