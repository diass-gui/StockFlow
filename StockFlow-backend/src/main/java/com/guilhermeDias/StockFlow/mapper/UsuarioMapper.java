package com.guilhermeDias.StockFlow.mapper;

import com.guilhermeDias.StockFlow.dto.Usuario.UsuarioRequestDTO;
import com.guilhermeDias.StockFlow.dto.Usuario.UsuarioResponseDTO;
import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.entity.Usuario;
import java.util.List;

public class UsuarioMapper {

    public static Usuario converterParaEntity(UsuarioRequestDTO requestDTO, Empresa empresa) {
        Usuario usuario = new Usuario();

        usuario.setNome(requestDTO.getNome());
        usuario.setCpf(requestDTO.getCpf());
        usuario.setEmail(requestDTO.getEmail());
        usuario.setSenha(requestDTO.getSenha());
        usuario.setEmpresa(empresa);

        return usuario;
    }

    public static UsuarioResponseDTO converterParaDTO(Usuario usuario) {
        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO();

        responseDTO.setId(usuario.getId());
        responseDTO.setNome(usuario.getNome());
        responseDTO.setCpf(usuario.getCpf());
        responseDTO.setEmail(usuario.getEmail());
        responseDTO.setRole(usuario.getRole());
        responseDTO.setEmpresaId(usuario.getEmpresa().getId());

        return responseDTO;
    }

    public static List<UsuarioResponseDTO> converterParaDTOList(List<Usuario> usuarios) {
        return usuarios.stream()
                .map(UsuarioMapper::converterParaDTO)
                .toList();
    }

}
