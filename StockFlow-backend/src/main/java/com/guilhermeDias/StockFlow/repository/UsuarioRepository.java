package com.guilhermeDias.StockFlow.repository;

import com.guilhermeDias.StockFlow.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    UserDetails findByEmail(String email);
}
