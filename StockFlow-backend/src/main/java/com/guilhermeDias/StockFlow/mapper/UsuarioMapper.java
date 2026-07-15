package com.guilhermeDias.StockFlow.mapper;

import com.guilhermeDias.StockFlow.dto.UsuarioRequestDTO;
import com.guilhermeDias.StockFlow.dto.UsuarioResponseDTO;
import com.guilhermeDias.StockFlow.entity.Usuario;
import java.util.List;

public class UsuarioMapper {

    public static Usuario converterParaEntity(UsuarioRequestDTO requestDTO) {
        Usuario usuario = new Usuario();

        usuario.setNome(requestDTO.getNome());
        usuario.setCpf(requestDTO.getCpf());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setSenha(requestDTO.getSenha());

        return usuario;
    }

    public static UsuarioResponseDTO converterParaDTO(Usuario usuario) {
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();

        responseDTO.setId(usuario.getId());
        responseDTO.setNome(usuario.getNome());
        responseDTO.setCpf(usuario.getCpf());
        responseDTO.setEmail(usuario.getEmail());
        responseDTO.setSenha(usuario.getSenha());

        return responseDTO;
    }

    public static List<UsuarioResponseDTO> converterParaDTOList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::converterParaDTO)
                .toList();
    }

}
