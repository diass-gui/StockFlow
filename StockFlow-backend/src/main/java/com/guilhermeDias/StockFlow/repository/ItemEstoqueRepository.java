package com.guilhermeDias.StockFlow.repository;

import com.guilhermeDias.StockFlow.entity.ItemEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemEstoqueRepository extends JpaRepository<ItemEstoque, Long> {
    boolean existsByEstoqueIdAndProdutoId(Long estoqueId, Long produtoId);
}
