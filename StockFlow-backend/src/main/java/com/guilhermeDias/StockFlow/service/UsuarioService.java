package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.entity.Usuario;
import com.guilhermeDias.StockFlow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarTodos() { return repository.findAll(); }

    public void salvar(Usuario usuario) { repository.save(usuario); }

}
