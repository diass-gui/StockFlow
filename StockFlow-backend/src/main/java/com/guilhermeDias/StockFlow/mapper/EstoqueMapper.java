package com.guilhermeDias.StockFlow.mapper;

import com.guilhermeDias.StockFlow.dto.EstoqueRequestDTO;
import com.guilhermeDias.StockFlow.dto.EstoqueResponseDTO;
import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.entity.Estoque;

import java.util.List;

public class EstoqueMapper {

    public static EstoqueResponseDTO converterParaDTO(Estoque estoque) {
        EstoqueResponseDTO responseDTO = new EstoqueResponseDTO();

        responseDTO.setId(estoque.getId());
        responseDTO.setNome(estoque.getNome());
        responseDTO.setEmpresaId(estoque.getEmpresa().getId());

        return responseDTO;
    }

    public static Estoque converterParaEntity(EstoqueRequestDTO requestDTO, Empresa empresa) {
        Estoque estoque = new Estoque();

        estoque.setNome(requestDTO.getNome());
        estoque.setEmpresa(empresa);

        return estoque;
    }

    public static List<EstoqueResponseDTO> converterParaDTOList(List<Estoque> estoques) {
        return estoques.stream()
                .map(EstoqueMapper::converterParaDTO)
                .toList();
    }

}
