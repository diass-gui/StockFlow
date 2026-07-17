package com.guilhermeDias.StockFlow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstoqueRequestDTO {

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotNull(message = "A empresa é obrigatória.")
    private Long empresaId;

}
