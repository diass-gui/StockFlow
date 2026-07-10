package com.guilhermeDias.StockFlow.dto;

import com.guilhermeDias.StockFlow.entity.Estoque;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
public class EmpresaRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "O CNPJ é obrigatório")
    @CNPJ(message = "Informe um CNPJ válido")
    private String cnpj;

    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

}
