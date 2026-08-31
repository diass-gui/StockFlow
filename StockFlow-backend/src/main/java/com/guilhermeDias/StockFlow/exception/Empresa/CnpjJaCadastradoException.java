package com.guilhermeDias.StockFlow.exception.Empresa;

public class CnpjJaCadastradoException extends RuntimeException {
    public CnpjJaCadastradoException() { super("CNPJ já cadastrado no sistema."); }
    public CnpjJaCadastradoException(String message) {
        super(message);
    }
}
