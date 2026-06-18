package com.guilhermeDias.StockFlow.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EstoqueResponseDTO {
    private Long id;
    private Long empresaId;
    private List<Long> produtosId; // aqui seria uma lista então?
}
