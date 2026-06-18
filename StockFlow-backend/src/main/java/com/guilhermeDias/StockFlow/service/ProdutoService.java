package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.entity.Produto;
import com.guilhermeDias.StockFlow.exception.ProdutoNaoEncontradoException;
import com.guilhermeDias.StockFlow.repository.EstoqueRepository;
import com.guilhermeDias.StockFlow.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public void salvar(Produto produto) {
        repository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));
    }

    public List<Produto> listarProdutosPorCategoria(String categoria) {
        return repository.findByCategoriaIgnoreCase(categoria);
    }

    public Produto atualizar(Long id, Produto produto) {
        Produto novoProduto = repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado."));

        novoProduto.setNome(produto.getNome());
        novoProduto.setQuantidade(produto.getQuantidade());
        novoProduto.setPreco(produto.getPreco());
        novoProduto.setCategoria(produto.getCategoria());
        novoProduto.setEstoque(produto.getEstoque());

        return repository.save(novoProduto);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}
