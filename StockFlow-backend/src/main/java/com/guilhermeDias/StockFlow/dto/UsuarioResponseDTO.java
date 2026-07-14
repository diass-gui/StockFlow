package com.guilhermeDias.StockFlow.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private Long empresaId;
}
