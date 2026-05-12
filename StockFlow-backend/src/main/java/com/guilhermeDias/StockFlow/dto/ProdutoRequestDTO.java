package com.guilhermeDias.StockFlow.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 0, message = "Quantidade não pode ser menor que zero.")
    private Integer quantidade;

    @NotNull(message = "O preço é obrigatório.")
    @Min(value = 0, message = "O preço não pode ser menor que zero.")
    private BigDecimal preco;

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria;

}
