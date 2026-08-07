package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.dto.Usuario.UsuarioRequestDTO;
import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.entity.Usuario;
import com.guilhermeDias.StockFlow.mapper.UsuarioMapper;
import com.guilhermeDias.StockFlow.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

//    @Autowired
//    private LoginRepository repository;

    @Autowired
    private EmpresaService empresaService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(email);
    }

    public void register(UsuarioRequestDTO requestDTO) {
        Empresa empresa = empresaService.buscarPorId(requestDTO.getEmpresaId());

        Usuario usuario = UsuarioMapper.converterParaEntity(requestDTO, empresa);

        String encryptedPassword = new BCryptPasswordEncoder().encode(requestDTO.getSenha());

        usuario.setSenha(encryptedPassword);

        usuarioRepository.save(usuario);
    }

}
