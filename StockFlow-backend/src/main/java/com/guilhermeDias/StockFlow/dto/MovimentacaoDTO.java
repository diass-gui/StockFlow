package com.guilhermeDias.StockFlow.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MovimentacaoDTO {

    @NotNull(message = "A quantidade é obrigatória.")
    @Min(0)
    private Integer quantidade;
}
