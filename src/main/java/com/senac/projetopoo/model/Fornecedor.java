package com.senac.projetopoo.model;

import jakarta.persistence.*;
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
public class Fornecedor extends BaseEntity{


    @Column(nullable = false, unique = true)
    private String cnpj;

    private String telefone;
    
    @Override
    public String toString(){
        return getNome();
    }

    public Fornecedor(String cnpj, String telefone, String nome, String descricao) {
        super(nome, descricao);
        this.cnpj = cnpj;
        this.telefone = telefone;
    }
    
   

}

