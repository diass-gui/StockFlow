package com.guilhermeDias.StockFlow.controller;

import com.guilhermeDias.StockFlow.dto.EstoqueRequestDTO;
import com.guilhermeDias.StockFlow.dto.EstoqueResponseDTO;
import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.entity.Estoque;
import com.guilhermeDias.StockFlow.mapper.EstoqueMapper;
import com.guilhermeDias.StockFlow.service.EmpresaService;
import com.guilhermeDias.StockFlow.service.EstoqueService;
import com.guilhermeDias.StockFlow.service.ProdutoService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {

    @Autowired
    private EstoqueService service;

    @Autowired
    private EmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<EstoqueResponseDTO>> listarEstoques() {
        return ResponseEntity.ok(EstoqueMapper.converterParaDTOList(service.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueResponseDTO> buscarEstoquePorId(@Valid @PathVariable Long id) {
        EstoqueResponseDTO response = EstoqueMapper.converterParaDTO(service.buscarEstoquePorId(id));

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Void> cadastrarEstoque(@RequestBody @Valid EstoqueRequestDTO requestDTO) {
        Empresa empresa = empresaService.buscarPorId(requestDTO.getEmpresaId());
        Estoque estoque = EstoqueMapper.converterParaEntity(requestDTO, empresa);

        service.salvar(estoque);

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerEstoque(@Valid @PathVariable Long id) {
        service.deletar(id);

        return ResponseEntity.status(204).build();
    }

}
