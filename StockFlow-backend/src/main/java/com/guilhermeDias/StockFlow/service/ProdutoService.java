package com.guilhermeDias.StockFlow.service;

import com.guilhermeDias.StockFlow.dto.ProdutoUpdateDTO;
import com.guilhermeDias.StockFlow.entity.Produto;
import com.guilhermeDias.StockFlow.exception.Produto.CategoriaInexistenteException;
import com.guilhermeDias.StockFlow.exception.Produto.ProdutoJaCadastradoException;
import com.guilhermeDias.StockFlow.exception.Produto.ProdutoNaoEncontradoException;
import com.guilhermeDias.StockFlow.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> listarTodos() {
        return repository.findAll();
    }

    public void salvar(Produto produto) {
        String nome = produto.getNome().trim().replaceAll("\\s+", " ");
        if(repository.existsByNome(nome)) {
            throw new ProdutoJaCadastradoException("Já existe um produto cadastrado com o mesmo nome.");
        }

        produto.setNome(nome);
        repository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));
    }

    public List<Produto> listarProdutosPorCategoria(String categoria) {
        List<Produto> produtos = repository.findByCategoriaIgnoreCase(categoria);

        if(produtos.isEmpty()) {
            throw new CategoriaInexistenteException();
        }

        return produtos;
    }

    public Produto atualizar(Long id, ProdutoUpdateDTO novoProduto) {
        Produto produto = buscarPorId(id);

//        String novoNome = novoProduto.getNome().trim().replaceAll("\\s+", " ");
//
//        if (!produto.getNome().equalsIgnoreCase(novoNome) && repository.existsByNome(novoNome)) {
//            throw new ProdutoJaCadastradoException("Não é possível alterar o nome do produto para um nome já cadastrado.");
//        }

//        produto.setNome(novoNome);
        produto.setPreco(novoProduto.getPreco());
        produto.setCategoria(novoProduto.getCategoria());

        return repository.save(produto);
    }

    public void remover(Long id) {
        Produto produto = buscarPorId(id);
        repository.delete(produto);
    }
}
