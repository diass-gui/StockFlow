package com.guilhermeDias.StockFlow.exception.Produto;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException() { super("Produto não encontrado."); }

    public ProdutoNaoEncontradoException(String message) {
        super(message);
    }
}
