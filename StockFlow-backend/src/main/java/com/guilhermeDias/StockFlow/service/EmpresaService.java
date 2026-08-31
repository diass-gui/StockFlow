package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.entity.Empresa;
import com.guilhermeDias.StockFlow.exception.Empresa.CnpjJaCadastradoException;
import com.guilhermeDias.StockFlow.exception.Empresa.EmailJaCadastradoException;
import com.guilhermeDias.StockFlow.exception.Empresa.EmpresaNaoEncontradaException;
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

    public Empresa salvar(Empresa empresa) {
        if(repository.existsByCnpj(empresa.getCnpj())) {
            throw new CnpjJaCadastradoException();
        }
        if(repository.existsByEmail(empresa.getEmail())) {
            throw new EmailJaCadastradoException();
        }
        return repository.save(empresa);
    }

    public Empresa buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EmpresaNaoEncontradaException("Empresa não encontrada"));
    }

    public void deletar(Long id) {
        Empresa empresa = buscarPorId(id);
        repository.delete(empresa);
    }

}
