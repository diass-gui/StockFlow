package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.repository.ItemEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemEstoqueService {

    @Autowired
    private ItemEstoqueRepository repository;

}
