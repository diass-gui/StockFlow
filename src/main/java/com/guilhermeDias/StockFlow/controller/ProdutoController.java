package com.guilhermeDias.StockFlow.controller;

import com.guilhermeDias.StockFlow.dto.ProdutoDTO;
import com.guilhermeDias.StockFlow.entity.Produto;
import com.guilhermeDias.StockFlow.mapper.ProdutoMapper;
import com.guilhermeDias.StockFlow.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Tag(name = "Produtos", description = "Controller para gerenciamento de produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @Operation(summary = "Buscar todos os produtos cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos encontrados")
    })
    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
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
    public ResponseEntity<ProdutoDTO> buscarProdutoPorId(@PathVariable @Valid Long id) {
        Produto produto = service.buscarPorId(id);

        ProdutoDTO produtoDTO = ProdutoMapper.converterParaDTO(produto);

        return ResponseEntity.ok(produtoDTO);
    }

    @Operation(summary = "Cadastrar o produto no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Produto foi cadastrado."),
            @ApiResponse(responseCode = "400", description = "Erro de validação."),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @PostMapping
    public ResponseEntity<Void> salvarProduto(@RequestBody @Valid ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapper.converterParaEntity(produtoDTO);

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
    public ResponseEntity<ProdutoDTO> atualizarProduto(@PathVariable @Valid Long id, @RequestBody @Valid ProdutoDTO produtoDTO) {
        Produto produto = ProdutoMapper.converterParaEntity(produtoDTO);

        Produto produtoAtualizado = service.atualizar(id, produto);

        ProdutoDTO atualizadoDTO = ProdutoMapper.converterParaDTO(produtoAtualizado);

        return ResponseEntity.ok(atualizadoDTO);
    }

    @Operation(summary = "Deleta/Remove um produto do sistema pelo ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro de validação"),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno/Servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(
            @Parameter(description = "ID do produto", example = "1")
            @PathVariable @Valid Long id) {
        service.remover(id);
        return ResponseEntity.status(204).build();
    }

}
