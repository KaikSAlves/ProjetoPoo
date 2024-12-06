package br.com.senacsp.projetopoo.crudapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;


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

    @ManyToOne(optional = false)
    private Fornecedor fornecedor;

    public Produto(double preco, int altura, int largura, int profundidade, int quantidadeEstoque, Marca marca, Fornecedor fornecedor, String nome, String descricao) {
        super(nome, descricao);
        this.preco = preco;
        this.altura = altura;
        this.largura = largura;
        this.profundidade = profundidade;
        this.quantidadeEstoque = quantidadeEstoque;
        this.marca = marca;
        this.fornecedor = fornecedor;
    }
    
    
}