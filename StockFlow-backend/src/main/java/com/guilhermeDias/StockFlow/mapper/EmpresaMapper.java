package com.guilhermeDias.StockFlow.mapper;

import com.guilhermeDias.StockFlow.dto.EmpresaRequestDTO;
import com.guilhermeDias.StockFlow.dto.EmpresaResponseDTO;
import com.guilhermeDias.StockFlow.entity.Empresa;
import java.util.List;

public class EmpresaMapper {

    public static EmpresaResponseDTO converterParaDTO(Empresa empresa) {
        EmpresaResponseDTO responseDTO = new EmpresaResponseDTO();

        responseDTO.setId(empresa.getId());
        responseDTO.setNome(empresa.getNome());
        responseDTO.setCnpj(empresa.getCnpj());
        responseDTO.setEmail(empresa.getEmail());

        if(empresa.getEstoque() != null) {
            responseDTO.setEstoqueId(empresa.getEstoque().getId());
        }

        return responseDTO;
    }

    public static Empresa converterParaEntity(EmpresaRequestDTO requestDTO) {
        Empresa empresa = new Empresa();

        empresa.setNome(requestDTO.getNome());
        empresa.setCnpj(requestDTO.getCnpj());
        empresa.setEmail(requestDTO.getEmail());

        return empresa;
    }

    public static List<EmpresaResponseDTO> converterParaDTOList(List<Empresa> empresas) {
        return empresas.stream()
                .map(EmpresaMapper::converterParaDTO)
                .toList();
    }

}
