package com.guilhermeDias.StockFlow.exception.Produto;

public class ProdutoJaCadastradoException extends RuntimeException {
    public ProdutoJaCadastradoException() { super("Produto já cadastrado no sistema."); }
    public ProdutoJaCadastradoException(String message) {
        super(message);
    }
}
