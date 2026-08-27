package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.dto.Usuario.UsuarioRequestDTO;
import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.entity.UserRole;
import com.guilhermeDias.StockFlow.entity.Usuario;
import com.guilhermeDias.StockFlow.exception.Usuario.UsuarioJaCadastradoException;
import com.guilhermeDias.StockFlow.mapper.UsuarioMapper;
import com.guilhermeDias.StockFlow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Usuário não encontrado."));
    }

    public void register(UsuarioRequestDTO requestDTO) {
        if(usuarioRepository.existsByCpf(requestDTO.getCpf())) {
            throw new UsuarioJaCadastradoException("Já existe um usuário cadastrado com o CPF informado.");
        }
        if(usuarioRepository.existsByEmail(requestDTO.getEmail())) {
            throw new UsuarioJaCadastradoException("Já existe um usuário cadastrado com o e-mail informado.");
        }

        Empresa empresa = empresaService.buscarPorId(requestDTO.getEmpresaId());
        Usuario usuario = UsuarioMapper.converterParaEntity(requestDTO, empresa);
        usuario.setSenha(passwordEncoder.encode(requestDTO.getSenha()));
        usuario.setRole(UserRole.USER);
        usuario.setAtivo(true);
        usuarioRepository.save(usuario);
    }

}
