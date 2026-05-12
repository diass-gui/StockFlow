package com.guilhermeDias.StockFlow.controller;

import com.guilhermeDias.StockFlow.dto.EmpresaRequestDTO;
import com.guilhermeDias.StockFlow.dto.EmpresaResponseDTO;
import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.entity.Produto;
import com.guilhermeDias.StockFlow.mapper.EmpresaMapper;
import com.guilhermeDias.StockFlow.service.EmpresaService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/empresas")
@Profile("dev")
public class EmpresaController {

    @Autowired
    private EmpresaService service;

    @GetMapping
    public ResponseEntity<List<EmpresaResponseDTO>> listarEmpresas() {
        return ResponseEntity.ok(EmpresaMapper.converterParaDTOList(service.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaResponseDTO> buscarProdutoPorId(@Valid @PathVariable Long id) {
        Empresa empresa = service.buscarPorId(id);

        EmpresaResponseDTO responseDTO = EmpresaMapper.converterParaDTO(empresa);

        return ResponseEntity.ok(responseDTO);
    }


    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> cadastrarEmpresa(@RequestBody @Valid EmpresaRequestDTO requestDTO) {
        Empresa empresa = EmpresaMapper.converterParaEntity(requestDTO);

        service.salvar(empresa);

        return ResponseEntity.status(201).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@Valid @PathVariable Long id) {
        service.deletar(id);

        return ResponseEntity.status(204).build();
    }

}
