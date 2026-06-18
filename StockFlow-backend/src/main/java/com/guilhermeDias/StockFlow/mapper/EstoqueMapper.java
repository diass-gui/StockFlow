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
        responseDTO.setEmpresaId(estoque.getEmpresa().getId());
        responseDTO.setProdutosId(
                estoque.getProdutos().stream()
                        .map(produto -> produto.getId())
                        .toList()
        );

        return responseDTO;
    }

    public static Estoque converterParaEntity(EstoqueRequestDTO requestDTO, Empresa empresa) {
        Estoque estoque = new Estoque();

        estoque.setEmpresa(empresa);

        return estoque;
    }

    public static List<EstoqueResponseDTO> converterParaDTOList(List<Estoque> estoques) {
        return estoques.stream()
                .map(EstoqueMapper::converterParaDTO)
                .toList();
    }

}
