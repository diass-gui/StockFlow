package com.guilhermeDias.StockFlow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemEstoqueResponseDTO {
    private Long id;
    private Long estoqueId;
    private Long produtoId;
    private Integer quantidade;
}
