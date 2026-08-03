package com.guilhermeDias.StockFlow.repository;

import com.guilhermeDias.StockFlow.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface LoginRepository extends JpaRepository<Usuario, String> {
    UserDetails findByEmail(String email);
}
