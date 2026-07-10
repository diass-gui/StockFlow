package com.guilhermeDias.StockFlow.controller;

import com.guilhermeDias.StockFlow.dto.EstoqueRequestDTO;
import com.guilhermeDias.StockFlow.dto.EstoqueResponseDTO;
import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.entity.Estoque;
import com.guilhermeDias.StockFlow.mapper.EstoqueMapper;
import com.guilhermeDias.StockFlow.service.EmpresaService;
import com.guilhermeDias.StockFlow.service.EstoqueService;
import com.guilhermeDias.StockFlow.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Estoque", description = "Controller para gerenciamento de estoques.")
@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @Autowired
    private EmpresaService empresaService;

    @Operation(summary = "Buscar todos os estoques cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno/servidor")
    })
    @GetMapping
    public ResponseEntity<List<EstoqueResponseDTO>> listarEstoques() {
        return ResponseEntity.ok(EstoqueMapper.converterParaDTOList(service.listar()));
    }

    @Operation(summary = "Busca o estoque pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estoque encontrado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> buscarEstoquePorId(@Valid @PathVariable Long id) {
        EstoqueResponseDTO response = EstoqueMapper.converterParaDTO(service.buscarEstoquePorId(id));

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Cadastrar o estoque no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estoque foi cadastrado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @PostMapping
    public ResponseEntity<Void> cadastrarEstoque(@RequestBody @Valid EstoqueRequestDTO requestDTO) {
        Empresa empresa = empresaService.buscarPorId(requestDTO.getEmpresaId());
        Estoque estoque = EstoqueMapper.converterParaEntity(requestDTO, empresa);

        service.salvar(estoque);

        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Deleta/Remove um estoque do sistema pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estoque removido com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "404", description = "Estoque não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEstoque(@Valid @PathVariable Long id) {
        service.deletar(id);

        return ResponseEntity.status(204).build();
    }

}
