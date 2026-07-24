package com.guilhermeDias.StockFlow.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemEstoqueRequestDTO {

    @NotNull(message = "O Estoque é obrigatório.")
    private Long estoqueId;

    @NotNull(message = "O Produto é obrigatório.")
    private Long produtoId;

    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 0, message = "Insira um valor válido (positivo)")
    private Integer quantidade;

}
