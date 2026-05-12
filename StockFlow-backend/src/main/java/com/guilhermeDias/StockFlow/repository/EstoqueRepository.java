package com.guilhermeDias.StockFlow.repository;

import com.guilhermeDias.StockFlow.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
}
