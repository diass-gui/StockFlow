package com.guilhermeDias.StockFlow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "DTO (Data Transfer Object) da entidade Produto")
public class ProdutoDTO {

    @Schema(description = "Nome do produto", example = "Sapato")
    private String nome;

    @Schema(description = "Quantidade do produto", example = "4")
    private Integer quantidade;

    @Schema(description = "Preço do produto", example = "74.90")
    private BigDecimal preco;

    @Schema(description = "Categoria do produto", example = "Calçados")
    private String categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
