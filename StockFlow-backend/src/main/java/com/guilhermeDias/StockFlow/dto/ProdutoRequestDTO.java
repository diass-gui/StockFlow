package com.guilhermeDias.StockFlow.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotNull(message = "O preço é obrigatório.")
    @Positive(message = "O valor deve ser maior que 0.00")
    private BigDecimal preco;

    @NotBlank(message = "A categoria é obrigatória.")
    private String categoria;

}
