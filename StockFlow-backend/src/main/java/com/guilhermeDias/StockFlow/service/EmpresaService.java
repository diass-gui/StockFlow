package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.exception.EmpresaNaoEncontradaException;
import com.guilhermeDias.StockFlow.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    private EmpresaRepository repository;

    public List<Empresa> listar() {
        return repository.findAll();
    }

    public void salvar(Empresa empresa) {
        repository.save(empresa);
    }

    public Empresa buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EmpresaNaoEncontradaException("Empresa não encontrada"));
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
