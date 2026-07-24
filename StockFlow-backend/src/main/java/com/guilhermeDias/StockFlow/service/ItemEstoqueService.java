package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.dto.ItemEstoqueRequestDTO;
import com.guilhermeDias.StockFlow.dto.MovimentacaoDTO;
import com.guilhermeDias.StockFlow.entity.Estoque;
import com.guilhermeDias.StockFlow.entity.ItemEstoque;
import com.guilhermeDias.StockFlow.entity.Produto;
import com.guilhermeDias.StockFlow.exception.ItemEstoque.ItemEstoqueJaCadastradoException;
import com.guilhermeDias.StockFlow.exception.ItemEstoque.ItemEstoqueNaoEncontradoException;
import com.guilhermeDias.StockFlow.exception.ItemEstoque.QuantidadeInsuficienteException;
import com.guilhermeDias.StockFlow.mapper.ItemEstoqueMapper;
import com.guilhermeDias.StockFlow.repository.ItemEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemEstoqueService {

    @Autowired
    private ItemEstoqueRepository repository;

    @Autowired
    private EstoqueService estoqueService;

    @Autowired
    private ProdutoService produtoService;

    public List<ItemEstoque> listarTodos() { return repository.findAll(); }

    public ItemEstoque buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new ItemEstoqueNaoEncontradoException()
        );
    }

    public void salvar(ItemEstoqueRequestDTO requestDTO) {
        if(repository.existsByEstoqueIdAndProdutoId(requestDTO.getEstoqueId(), requestDTO.getProdutoId())) {
            throw new ItemEstoqueJaCadastradoException();
        }

        Estoque estoque = estoqueService.buscarEstoquePorId(requestDTO.getEstoqueId());
        Produto produto = produtoService.buscarPorId(requestDTO.getProdutoId());
        ItemEstoque itemEstoque = ItemEstoqueMapper.converterParaEntity(requestDTO, estoque, produto);

        repository.save(itemEstoque);
    }

    // métodos para aumentar/diminuir a quantidade (movimentação de estoque)
    public void aumentarQuantidadeItem(Long itemId, MovimentacaoDTO movimentacao) {
        ItemEstoque itemEstoque = buscarPorId(itemId);

        Integer qtdAtual = itemEstoque.getQuantidade();
        itemEstoque.setQuantidade(qtdAtual + movimentacao.getQuantidade());

        repository.save(itemEstoque);
    }

    public void diminuirQuantidadeItem(Long itemId, MovimentacaoDTO movimentacaoDTO) {
        ItemEstoque itemEstoque = buscarPorId(itemId);

        if(movimentacaoDTO.getQuantidade() > itemEstoque.getQuantidade()) {
            throw new QuantidadeInsuficienteException();
        }

        Integer qtdAtual = itemEstoque.getQuantidade();
        itemEstoque.setQuantidade(qtdAtual - movimentacaoDTO.getQuantidade());

        repository.save(itemEstoque);
    }

    public void deletar(Long id) {
        ItemEstoque itemEstoque = buscarPorId(id);
        repository.delete(itemEstoque);
    }

}
