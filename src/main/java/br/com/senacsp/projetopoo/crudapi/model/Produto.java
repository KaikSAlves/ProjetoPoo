package br.com.senacsp.projetopoo.crudapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table
public class Produto extends BaseEntity {

    private double preco;
    private int altura;
    private int largura;
    private int profundidade;

    @Column(nullable = false)
    private int quantidadeEstoque;

    @ManyToOne
    private Marca marca;

    @ManyToOne
    private Categoria categoria;

    @ManyToOne(optional = false)
    private Fornecedor fornecedor;
}