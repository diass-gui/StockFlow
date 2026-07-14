package com.guilhermeDias.StockFlow.controller;

import com.guilhermeDias.StockFlow.dto.UsuarioRequestDTO;
import com.guilhermeDias.StockFlow.dto.UsuarioResponseDTO;
import com.guilhermeDias.StockFlow.entity.Usuario;
import com.guilhermeDias.StockFlow.mapper.UsuarioMapper;
import com.guilhermeDias.StockFlow.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Usuario", description = "Controller para gerenciamento de usuários.")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(UsuarioMapper.converterParaDTOList(service.listarTodos()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPeloId(@PathVariable @Valid Long id) {
        UsuarioResponseDTO responseDTO = UsuarioMapper.converterParaDTO(service.buscarPeloId(id));
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody @Valid UsuarioRequestDTO requestDTO) {
        service.salvar(UsuarioMapper.converterParaEntity(requestDTO));
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable @Valid Long id, @RequestBody @Valid UsuarioRequestDTO requestDTO) {
        Usuario usuario = UsuarioMapper.converterParaEntity(requestDTO);

        return ResponseEntity.ok(UsuarioMapper.converterParaDTO(service.atualizarUsuario(id, usuario)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable @Valid Long id) {
        service.removerUsuario(id);
        return ResponseEntity.status(204).build();
    }

}
