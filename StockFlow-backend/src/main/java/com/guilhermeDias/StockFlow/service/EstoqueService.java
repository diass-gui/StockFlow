package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.entity.Estoque;
import com.guilhermeDias.StockFlow.exception.EstoqueNaoEncontradoException;
import com.guilhermeDias.StockFlow.repository.EstoqueRepository;
import com.guilhermeDias.StockFlow.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    @Autowired
    private EstoqueRepository repository;

    public List<Estoque> listar() {
        return repository.findAll();
    }

    public Estoque buscarEstoquePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EstoqueNaoEncontradoException("Estoque não encontrado")
                );
    }

    public void salvar(Estoque estoque) {
        repository.save(estoque);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
