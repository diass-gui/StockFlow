package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.dto.Usuario.UsuarioRequestDTO;
import com.guilhermeDias.StockFlow.entity.Usuario;
import com.guilhermeDias.StockFlow.exception.Usuario.UsuarioNaoEncontradoException;
import com.guilhermeDias.StockFlow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private EmpresaService empresaService;

    public List<Usuario> listarTodos() { return repository.findAll(); }

    public Usuario buscarPeloId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException("O usuário não foi encontrado.")
        );
    }

    public void desativarUsuario(Long id) {
        Usuario usuario = buscarPeloId(id);
        usuario.setAtivo(false);
        repository.save(usuario);
    }

}
