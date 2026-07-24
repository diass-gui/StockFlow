package com.guilhermeDias.StockFlow.exception.ItemEstoque;

public class QuantidadeInsuficienteException extends RuntimeException {
    public QuantidadeInsuficienteException() { super("Não há quantidade o suficiente do item para realizar a movimentação."); }
    public QuantidadeInsuficienteException(String message) {
        super(message);
    }
}
