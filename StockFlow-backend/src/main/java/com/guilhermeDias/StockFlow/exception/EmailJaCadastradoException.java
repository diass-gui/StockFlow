package com.guilhermeDias.StockFlow.exception;

public class EmailJaCadastradoException extends RuntimeException {
    public EmailJaCadastradoException() { super("E-mail já cadastrado no sistema."); }
    public EmailJaCadastradoException(String message) {
        super(message);
    }
}
