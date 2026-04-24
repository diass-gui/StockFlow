package com.guilhermeDias.StockFlow.repository;

import com.guilhermeDias.StockFlow.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

//    Produto findById(Long id);

}
