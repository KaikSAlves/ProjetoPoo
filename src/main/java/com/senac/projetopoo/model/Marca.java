package com.senac.projetopoo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor

@Table
@Entity
public class Marca extends BaseEntity{

    @Column(columnDefinition = "mediumblob")
    private byte[] logo;

    public Marca(String nome, String descricao, byte[] logo){
        super(nome, descricao);
        this.logo = logo;
    }

    @Override
    public String toString(){
        return getNome();
    }
}
