package com.guilhermeDias.StockFlow.exception.ItemEstoque;

public class ItemEstoqueNaoEncontradoException extends RuntimeException {
    public ItemEstoqueNaoEncontradoException() { super("O Item informado não foi encontrado no sistema."); }
    public ItemEstoqueNaoEncontradoException(String message) {
        super(message);
    }
}
