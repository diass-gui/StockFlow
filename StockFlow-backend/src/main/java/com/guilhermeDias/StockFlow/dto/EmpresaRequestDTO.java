package com.guilhermeDias.StockFlow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

    @Email(message = "Informe um e-mail válido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

}
