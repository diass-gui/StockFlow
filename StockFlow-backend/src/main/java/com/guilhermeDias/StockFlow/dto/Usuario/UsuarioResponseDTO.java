package com.guilhermeDias.StockFlow.dto.Usuario;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private Boolean admin;
    private Long empresaId;
}
