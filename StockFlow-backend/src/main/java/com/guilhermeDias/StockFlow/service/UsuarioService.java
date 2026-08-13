package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.dto.Usuario.UsuarioRequestDTO;
import com.guilhermeDias.StockFlow.entity.Usuario;
import com.guilhermeDias.StockFlow.exception.Usuario.UsuarioJaCadastradoException;
import com.guilhermeDias.StockFlow.exception.Usuario.UsuarioNaoEncontradoException;
import com.guilhermeDias.StockFlow.mapper.UsuarioMapper;
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

    public void salvar(UsuarioRequestDTO requestDTO) {
        if(repository.existsByCpf(requestDTO.getCpf())) {
            throw new UsuarioJaCadastradoException("Já existe um usuário cadastrado com o CPF informado.");
        }
        if(repository.existsByEmail(requestDTO.getEmail())) {
            throw new UsuarioJaCadastradoException("Já existe um usuário cadastrado com o e-mail informado.");
        }

        Usuario usuario = UsuarioMapper.converterParaEntity(requestDTO, empresaService.buscarPorId(requestDTO.getEmpresaId()));
        repository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, UsuarioRequestDTO requestDTO) {
        Usuario novoUsuario = repository.findById(id).orElseThrow(
                () -> new UsuarioNaoEncontradoException("O usuário não foi encontrado.")
        );

        novoUsuario.setNome(requestDTO.getNome());
        novoUsuario.setCpf(requestDTO.getCpf());
        novoUsuario.setEmail(requestDTO.getEmail());
        novoUsuario.setSenha(requestDTO.getSenha());
        novoUsuario.setEmpresa(empresaService.buscarPorId(requestDTO.getEmpresaId()));

        return repository.save(novoUsuario);
    }

    public void removerUsuario(Long id) {
        Usuario usuario = buscarPeloId(id);
        repository.delete(usuario);
    }

}
