package com.guilhermeDias.StockFlow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EstoqueRequestDTO {

    @NotNull(message = "A empresa é obrigatória.")
    private Long empresaId;

    private List<Long> produtosId;

}
