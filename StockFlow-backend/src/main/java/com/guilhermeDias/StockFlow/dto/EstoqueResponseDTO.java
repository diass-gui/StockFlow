package com.guilhermeDias.StockFlow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueResponseDTO {
    private Long id;
    private String nome;
    private Long empresaId;
}
