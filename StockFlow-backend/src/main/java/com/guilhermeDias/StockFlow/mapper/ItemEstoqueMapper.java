package com.guilhermeDias.StockFlow.mapper;

import com.guilhermeDias.StockFlow.dto.ItemEstoqueRequestDTO;
import com.guilhermeDias.StockFlow.dto.ItemEstoqueResponseDTO;
import com.guilhermeDias.StockFlow.entity.Estoque;
import com.guilhermeDias.StockFlow.entity.ItemEstoque;
import com.guilhermeDias.StockFlow.entity.Produto;
import java.util.List;

public class ItemEstoqueMapper {

    public static ItemEstoqueResponseDTO converterParaDTO(ItemEstoque itemEstoque) {
        ItemEstoqueResponseDTO responseDTO = new ItemEstoqueResponseDTO();

        responseDTO.setId(itemEstoque.getId());
        responseDTO.setEstoqueId(itemEstoque.getEstoque().getId());
        responseDTO.setProdutoId(itemEstoque.getProduto().getId());
        responseDTO.setQuantidade(itemEstoque.getQuantidade());

        return responseDTO;
    }

    public static ItemEstoque converterParaEntity(ItemEstoqueRequestDTO requestDTO, Estoque estoque, Produto produto) {
        ItemEstoque itemEstoque = new ItemEstoque();

        itemEstoque.setEstoque(estoque);
        itemEstoque.setProduto(produto);
        itemEstoque.setQuantidade(requestDTO.getQuantidade());

        return itemEstoque;
    }

    public static List<ItemEstoqueResponseDTO> converterParaDTOList(List<ItemEstoque> itens) {
        return itens.stream()
                .map(ItemEstoqueMapper::converterParaDTO)
                .toList();
    }

}
