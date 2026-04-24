package com.guilhermeDias.StockFlow.mapper;

import com.guilhermeDias.StockFlow.dto.ProdutoDTO;
import com.guilhermeDias.StockFlow.entity.Produto;

import java.util.List;

public class ProdutoMapper {

    public static ProdutoDTO converterParaDTO(Produto produto) {
        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setNome(produto.getNome());
        produtoDTO.setQuantidade(produto.getQuantidade());
        produtoDTO.setPreco(produto.getPreco());
        produtoDTO.setCategoria(produto.getCategoria());

        return produtoDTO;
    }

    public static Produto converterParaEntity(ProdutoDTO produtoDTO) {
        Produto produto = new Produto();

        produto.setNome(produtoDTO.getNome());
        produto.setQuantidade(produtoDTO.getQuantidade());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(produtoDTO.getCategoria());

        return produto;
    }

    public static List<ProdutoDTO> converterParaDTOList(List<Produto> produtos) {
            return produtos.stream()
                    .map(ProdutoMapper::converterParaDTO)
                    .toList();
    }

}
