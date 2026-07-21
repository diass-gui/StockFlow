package com.guilhermeDias.StockFlow.controller;

import com.guilhermeDias.StockFlow.dto.ProdutoRequestDTO;
import com.guilhermeDias.StockFlow.dto.ProdutoResponseDTO;
import com.guilhermeDias.StockFlow.dto.ProdutoUpdateDTO;
import com.guilhermeDias.StockFlow.entity.Estoque;
import com.guilhermeDias.StockFlow.entity.Produto;
import com.guilhermeDias.StockFlow.mapper.ProdutoMapper;
import com.guilhermeDias.StockFlow.service.EstoqueService;
import com.guilhermeDias.StockFlow.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@Tag(name = "Produto", description = "Controller para gerenciamento de produtos.")
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Operation(summary = "Buscar todos os produtos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados"),
            @ApiResponse(responseCode = "500", description = "Erro interno/servidor")
    })
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        return ResponseEntity.ok(ProdutoMapper.converterParaDTOList(service.listarTodos()));
    }

    @Operation(summary = "Busca o produto pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto encontrado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(@PathVariable @Valid Long id) {
        ProdutoResponseDTO responseDTO = ProdutoMapper.converterParaDTO(service.buscarPorId(id));
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Buscar e listar todos os produtos cadastrados com a categoria informada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados"),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "404", description = "Categoria não encontrada."),
            @ApiResponse(responseCode = "500", description = "Erro interno/servidor.")
    })
    @GetMapping("/categorias/{categoria}")
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutosPorCategoria(@PathVariable @Valid String categoria) {
        List<Produto> produtos = service.listarProdutosPorCategoria(categoria);
        List<ProdutoResponseDTO> responseDTO = ProdutoMapper.converterParaDTOList(produtos);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Cadastrar o produto no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto foi cadastrado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "409", description = "Produto já existe no sistema."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @PostMapping
    public ResponseEntity<Void> salvarProduto(@RequestBody @Valid ProdutoRequestDTO requestDTO) {
        Produto produto = ProdutoMapper.converterParaEntity(requestDTO);
        service.salvar(produto);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "Atualiza o Produto conforme o ID e o JSON contendo os dados atualizados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "O produto foi atualizado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable @Valid Long id, @RequestBody @Valid ProdutoUpdateDTO updateDTO) {
//        Produto produto = ProdutoMapper.converterParaEntity(requestDTO);
        Produto produtoAtualizado = service.atualizar(id, updateDTO);
        ProdutoResponseDTO responseDTO = ProdutoMapper.converterParaDTO(produtoAtualizado);
        return ResponseEntity.ok(responseDTO);
    }

    @Operation(summary = "Deleta/Remove um produto do sistema pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto (
            @Parameter(description = "ID do produto", example = "1")
            @PathVariable @Valid Long id) {
        service.remover(id);
        return ResponseEntity.status(204).build();
    }

}
