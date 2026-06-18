package com.guilhermeDias.StockFlow.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "estoque")
    private List<Produto> produtos;

    @OneToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

}
