package com.guilhermeDias.StockFlow.controller;

import com.guilhermeDias.StockFlow.dto.EmpresaRequestDTO;
import com.guilhermeDias.StockFlow.dto.EmpresaResponseDTO;
import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.entity.Produto;
import com.guilhermeDias.StockFlow.mapper.EmpresaMapper;
import com.guilhermeDias.StockFlow.service.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Tag(name = "Empresa", description = "Controller para gerenciamento de empresas.")
@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @Operation(summary = "Buscar todos as empresas cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresas encontradas"),
            @ApiResponse(responseCode = "500", description = "Erro interno/servidor")
    })
    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> listarEmpresas() {
        return ResponseEntity.ok(EmpresaMapper.converterParaDTOList(service.listar()));
    }

    @Operation(summary = "Busca a empresa pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Empresa encontrada."),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> buscarEmpresaPorId(@Valid @PathVariable Long id) {
        Empresa empresa = service.buscarPorId(id);
        EmpresaResponseDTO responseDTO = EmpresaMapper.converterParaDTO(empresa);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Cadastrar a empresa no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empresa foi cadastrada."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> cadastrarEmpresa(@RequestBody @Valid EmpresaRequestDTO requestDTO) {
        Empresa empresa = EmpresaMapper.converterParaEntity(requestDTO);
        service.salvar(empresa);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Deleta/Remove uma empresa do sistema pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Empresa removida com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "404", description = "Empresa não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@Valid @PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }

}
