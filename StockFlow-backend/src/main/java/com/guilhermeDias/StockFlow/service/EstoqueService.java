package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.dto.EstoqueRequestDTO;
import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.entity.Estoque;
import com.guilhermeDias.StockFlow.exception.EstoqueJaCadastradoException;
import com.guilhermeDias.StockFlow.exception.EstoqueNaoEncontradoException;
import com.guilhermeDias.StockFlow.mapper.EstoqueMapper;
import com.guilhermeDias.StockFlow.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository repository;

    @Autowired
    private EmpresaService empresaService;

    public List<Estoque> listar() {
        return repository.findAll();
    }

    public Estoque buscarEstoquePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EstoqueNaoEncontradoException("Estoque não encontrado")
                );
    }

    public void salvar(EstoqueRequestDTO requestDTO) {
        if(repository.existsByNomeAndEmpresaId(requestDTO.getNome().trim(), requestDTO.getEmpresaId())) {
            throw new EstoqueJaCadastradoException("Já existe um estoque cadastrado com os dados informados no sistema.");
        }

        Empresa empresa = empresaService.buscarPorId(requestDTO.getEmpresaId());
        Estoque estoque = EstoqueMapper.converterParaEntity(requestDTO, empresa);

        repository.save(estoque);
    }

    public void deletar(Long id) {
        Estoque estoque = buscarEstoquePorId(id);
        repository.delete(estoque);
    }
}
