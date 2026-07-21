package com.guilhermeDias.StockFlow.service;

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

    public List<Usuario> listarTodos() { return repository.findAll(); }

    public Usuario buscarPeloId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException("O usuário não foi encontrado.")
        );
    }

    public void salvar(Usuario usuario) { repository.save(usuario); }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        Usuario novoUsuario = repository.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException("O usuário não foi encontrado.")
        );

        novoUsuario.setNome(usuario.getNome());
        novoUsuario.setCpf(usuario.getCpf());
        novoUsuario.setEmail(usuario.getEmail());
        novoUsuario.setSenha(usuario.getSenha());
        novoUsuario.setEmpresa(usuario.getEmpresa());

        return repository.save(novoUsuario);
    }

    public void removerUsuario(Long id) { repository.deleteById(id); }

}
