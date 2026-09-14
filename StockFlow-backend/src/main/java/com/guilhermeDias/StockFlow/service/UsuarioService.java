package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.dto.Usuario.UsuarioUpdateDTO;
import com.guilhermeDias.StockFlow.entity.Usuario;
import com.guilhermeDias.StockFlow.exception.Usuario.UsuarioAtivoException;
import com.guilhermeDias.StockFlow.exception.Usuario.UsuarioDesativadoException;
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

    public void desativarUsuario(UsuarioUpdateDTO updateDTO) {
        Usuario usuario = repository.findByEmail(updateDTO.getEmail()).orElseThrow(
                () -> new UsuarioNaoEncontradoException("O usuário informado não foi encontrado.")
        );

        if(!usuario.isAtivo()) {
            throw new UsuarioDesativadoException("O usuário já está desativado no sistema.");
        }

        usuario.setAtivo(false);
        repository.save(usuario);
    }

    public void reativarUsuario(UsuarioUpdateDTO updateDTO) {
        Usuario usuario = repository.findByEmail(updateDTO.getEmail()).orElseThrow(
                () -> new UsuarioNaoEncontradoException("O usuário informado não foi encontrado.")
        );

        if(usuario.isAtivo()) {
            throw new UsuarioAtivoException("O usuário já está ativo no sistema.");
        }

        usuario.setAtivo(true);
        repository.save(usuario);
    }

}
