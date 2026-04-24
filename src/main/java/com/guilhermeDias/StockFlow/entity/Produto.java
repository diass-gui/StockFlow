package com.guilhermeDias.StockFlow.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
//@Table(name = "tb_produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(nullable = false, unique = true)
    private String nome;

    @NotNull(message = "A quantidade é obrigatória.")
    @Min(value = 0, message = "Quantidade não pode ser menor que zero.")
    @Column
    private Integer quantidade;

    @NotNull(message = "O preço é obrigatório.")
    @Min(value = 0, message = "O preço não pode ser menor que zero.")
    @Column
    private BigDecimal preco;

    @NotBlank(message = "A categoria é obrigatória.")
    @Column
    private String categoria;

}
