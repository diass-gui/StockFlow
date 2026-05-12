package com.guilhermeDias.StockFlow.repository;

import com.guilhermeDias.StockFlow.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
