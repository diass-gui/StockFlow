package com.guilhermeDias.StockFlow.repository;

import com.guilhermeDias.StockFlow.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
