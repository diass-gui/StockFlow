package com.guilhermeDias.StockFlow.dto.Usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UsuarioUpdateDTO {
    @Email(message = "Informe um e-mail válido.")
    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

}
