package com.guilhermeDias.StockFlow.repository;

import com.guilhermeDias.StockFlow.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    boolean existsByCnpj(String cnpj);
    boolean existsByEmail(String email);

}
