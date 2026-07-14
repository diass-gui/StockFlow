package com.guilhermeDias.StockFlow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
public class UsuarioRequestDTO {

    @NotBlank(message = "O Nome do usuaŕio é obrigatório.")
    private String nome;

    @CPF(message = "Informe um CPF válido.")
    @NotBlank(message = "O CPF é obrigatório.")
    private String cpf;

    @Email(message = "Informe um e-mail válido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

    @NotBlank(message = "A senha é obrigatória.")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    private String senha;

}
