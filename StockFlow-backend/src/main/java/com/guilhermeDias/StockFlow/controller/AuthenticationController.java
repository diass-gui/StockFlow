package com.guilhermeDias.StockFlow.controller;

import com.guilhermeDias.StockFlow.dto.Login.AuthenticationDTO;
import com.guilhermeDias.StockFlow.dto.Login.LoginResponseDTO;
import com.guilhermeDias.StockFlow.dto.Login.RegisterDTO;
import com.guilhermeDias.StockFlow.dto.Usuario.UsuarioRequestDTO;
import com.guilhermeDias.StockFlow.entity.Usuario;
import com.guilhermeDias.StockFlow.infra.TokenService;
import com.guilhermeDias.StockFlow.mapper.UsuarioMapper;
import com.guilhermeDias.StockFlow.repository.LoginRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoginRepository repository;

    @Autowired
    private TokenService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        var emailPassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(emailPassword);

        var token = service.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UsuarioRequestDTO requestDTO) {
        if(repository.findByEmail(requestDTO.getEmail()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(requestDTO.getSenha());
//        Usuario novoUsuario = UsuarioMapper.converterParaEntity(requestDTO)

        this.repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

}
