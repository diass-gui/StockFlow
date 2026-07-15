package com.guilhermeDias.StockFlow.exception;

public class CnpjJaCadastradoException extends RuntimeException {
    public CnpjJaCadastradoException() { super("CNPJ já cadastrado no sistema."); }
    public CnpjJaCadastradoException(String message) {
        super(message);
    }
}
