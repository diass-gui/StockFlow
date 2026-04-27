package com.guilhermeDias.StockFlow.repository;

import com.guilhermeDias.StockFlow.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByCategoriaIgnoreCase(String categoria);
//    List<Produto> findByPrecoLessThan(BigDecimal preco);
//    List<Produto> findyByPrecoGreaterThan(BigDecimal preco);

}
