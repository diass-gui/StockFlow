package com.guilhermeDias.StockFlow.mapper;

import com.guilhermeDias.StockFlow.dto.ProdutoRequestDTO;
import com.guilhermeDias.StockFlow.dto.ProdutoResponseDTO;
import com.guilhermeDias.StockFlow.entity.Estoque;
import com.guilhermeDias.StockFlow.entity.Produto;
import java.util.List;

public class ProdutoMapper {

    public static ProdutoResponseDTO converterParaDTO(Produto produto) {
        ProdutoResponseDTO responseDTO = new ProdutoResponseDTO();

        responseDTO.setId(produto.getId());
        responseDTO.setNome(produto.getNome());
        responseDTO.setQuantidade(produto.getQuantidade());
        responseDTO.setPreco(produto.getPreco());
        responseDTO.setCategoria(produto.getCategoria());
        responseDTO.setEstoqueId(produto.getEstoque().getId());

        return responseDTO;
    }

    public static Produto converterParaEntity(ProdutoRequestDTO requestDTO, Estoque estoque) {
        Produto produto = new Produto();

        produto.setNome(requestDTO.getNome());
        produto.setQuantidade(requestDTO.getQuantidade());
        produto.setPreco(requestDTO.getPreco());
        produto.setCategoria(requestDTO.getCategoria());
        produto.setEstoque(estoque);

        return produto;
    }

    public static List<ProdutoResponseDTO> converterParaDTOList(List<Produto> produtos) {
            return produtos.stream()
                    .map(ProdutoMapper::converterParaDTO)
                    .toList();
    }

}
