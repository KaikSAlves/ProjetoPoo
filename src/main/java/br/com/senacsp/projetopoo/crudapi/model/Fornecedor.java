package br.com.senacsp.projetopoo.crudapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table
public class Fornecedor extends BaseEntity{


    @Column(nullable = false, unique = true)
    private String cnpj;

    private String telefone;
    
    public Fornecedor(String nome, String descricao, String cnpj, String telefone){
        super(nome, descricao);
        this.cnpj = cnpj;
        this.telefone = telefone;
    }

    @Override
    public String toString(){
        return getNome();
    }

}

