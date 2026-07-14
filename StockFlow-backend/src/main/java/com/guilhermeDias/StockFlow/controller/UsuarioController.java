package com.guilhermeDias.StockFlow.controller;

import com.guilhermeDias.StockFlow.dto.UsuarioRequestDTO;
import com.guilhermeDias.StockFlow.dto.UsuarioResponseDTO;
import com.guilhermeDias.StockFlow.entity.Usuario;
import com.guilhermeDias.StockFlow.mapper.UsuarioMapper;
import com.guilhermeDias.StockFlow.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Buscar todos os usuários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários encontrados."),
            @ApiResponse(responseCode = "500", description = "Erro interno/servidor.")
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(UsuarioMapper.converterParaDTOList(service.listarTodos()));
    }

    @Operation(summary = "Busca o usuário pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarUsuarioPeloId(@PathVariable @Valid Long id) {
        UsuarioResponseDTO responseDTO = UsuarioMapper.converterParaDTO(service.buscarPeloId(id));
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Cadastrar o usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário foi cadastrado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor.")
    })
    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody @Valid UsuarioRequestDTO requestDTO) {
        service.salvar(UsuarioMapper.converterParaEntity(requestDTO));
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Atualiza o usuário conforme o ID e o JSON contendo os dados atualizados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O usuário foi atualizado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarUsuario(@PathVariable @Valid Long id, @RequestBody @Valid UsuarioRequestDTO requestDTO) {
        Usuario usuario = UsuarioMapper.converterParaEntity(requestDTO);

        return ResponseEntity.ok(UsuarioMapper.converterParaDTO(service.atualizarUsuario(id, usuario)));
    }

    @Operation(summary = "Deleta/Remove um usuário do sistema pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable @Valid Long id) {
        service.removerUsuario(id);
        return ResponseEntity.status(204).build();
    }

}
