package com.guilhermeDias.StockFlow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoUpdateDTO {

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O valor deve ser maior que 0.00")
    private BigDecimal preco;

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria;

}
