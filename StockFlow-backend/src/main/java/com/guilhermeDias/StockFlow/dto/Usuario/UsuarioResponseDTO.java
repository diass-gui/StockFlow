package com.guilhermeDias.StockFlow.dto.Usuario;

import com.guilhermeDias.StockFlow.entity.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponseDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String email;
    private UserRole role;
    private Long empresaId;
}
