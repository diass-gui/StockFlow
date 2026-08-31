package com.guilhermeDias.StockFlow.exception.ItemEstoque;

public class ItemEstoqueJaCadastradoException extends RuntimeException {
    public ItemEstoqueJaCadastradoException() { super("Já existe um Item cadastrado com o estoque e produto informado."); }
    public ItemEstoqueJaCadastradoException(String message) {
        super(message);
    }
}
