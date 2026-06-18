package com.guilhermeDias.StockFlow.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private Integer quantidade;
    private BigDecimal preco;
    private String categoria;
    private Long estoqueId;
}
