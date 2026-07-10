package com.guilhermeDias.StockFlow.dto;

import com.guilhermeDias.StockFlow.entity.Estoque;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaResponseDTO {
    private Long id;
    private String nome;
    private String cnpj;
    private String email;
    private Long estoqueId;
}
