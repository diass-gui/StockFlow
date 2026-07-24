package com.guilhermeDias.StockFlow.controller;

import com.guilhermeDias.StockFlow.dto.ItemEstoqueRequestDTO;
import com.guilhermeDias.StockFlow.dto.ItemEstoqueResponseDTO;
import com.guilhermeDias.StockFlow.dto.MovimentacaoDTO;
import com.guilhermeDias.StockFlow.mapper.ItemEstoqueMapper;
import com.guilhermeDias.StockFlow.service.ItemEstoqueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "ItemEstoque", description = "Controller para gerenciamento dos itens de um estoque.")
@RestController
@RequestMapping("/api/itens-estoque")
public class ItemEstoqueController {

    @Autowired
    private ItemEstoqueService service;

    @Operation(summary = "Buscar todos os itens cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Itens encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno/servidor")
    })
    @GetMapping
    public ResponseEntity<List<ItemEstoqueResponseDTO>> listarItens() {
        return ResponseEntity.ok(ItemEstoqueMapper.converterParaDTOList(service.listarTodos()));
    }

    @Operation(summary = "Busca o item pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item encontrado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "404", description = "Item não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ItemEstoqueResponseDTO> buscarItemPorId(@PathVariable @Valid Long id) {
        ItemEstoqueResponseDTO responseDTO = ItemEstoqueMapper.converterParaDTO(service.buscarPorId(id));
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Cadastrar um item no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "O item foi cadastrado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "409", description = "Item já existente no sistema."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @PostMapping
    public ResponseEntity<Void> salvarItem(@RequestBody @Valid ItemEstoqueRequestDTO requestDTO) {
        service.salvar(requestDTO);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Realizar a movimentação de entrada (aumento de quantidade) de um item no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "A movimentação foi concluída com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @PostMapping("/{id}/entrada")
    public ResponseEntity<Void> entradaItem(@PathVariable @Valid Long id, @RequestBody @Valid MovimentacaoDTO movimentacao) {
        service.aumentarQuantidadeItem(id, movimentacao);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "Realizar a movimentação de saída (diminuição de quantidade) de um item no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "A movimentação foi concluída com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "409", description = "Quantidade inferior a quantidade de retirada."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @PostMapping("/{id}/saida")
    public ResponseEntity<Void> saidaitem(@PathVariable @Valid Long id, @RequestBody @Valid MovimentacaoDTO movimentacao) {
        service.diminuirQuantidadeItem(id, movimentacao);
        return ResponseEntity.status(204).build();
    }

    @Operation(summary = "Remove um item do sistema pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Item removido com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "404", description = "Item não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem (
            @Parameter(description = "ID do produto", example = "1")
            @PathVariable @Valid Long id) {
        service.deletar(id);
        return ResponseEntity.status(204).build();
    }

}
