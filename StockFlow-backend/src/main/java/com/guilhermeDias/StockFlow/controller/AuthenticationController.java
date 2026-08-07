package com.guilhermeDias.StockFlow.controller;

import com.guilhermeDias.StockFlow.dto.Login.AuthenticationDTO;
import com.guilhermeDias.StockFlow.dto.Login.LoginResponseDTO;
import com.guilhermeDias.StockFlow.dto.Usuario.UsuarioRequestDTO;
import com.guilhermeDias.StockFlow.entity.Usuario;
import com.guilhermeDias.StockFlow.infra.TokenService;
import com.guilhermeDias.StockFlow.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private LoginRepository repository;

    @Autowired
    private TokenService service;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO dto) {
        var emailPassword = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var auth = this.authenticationManager.authenticate(emailPassword);

        var token = service.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid UsuarioRequestDTO requestDTO) {
//        if(repository.findByEmail(requestDTO.getEmail()) != null) return ResponseEntity.badRequest().build();
//
//        String encryptedPassword = new BCryptPasswordEncoder().encode(requestDTO.getSenha());
//        Usuario novoUsuario = new Usuario();
//
//        novoUsuario.setNome(requestDTO.getNome());
//
//        this.repository.save(novoUsuario);

        authService.register(requestDTO);

        return ResponseEntity.ok().build();
    }

}
