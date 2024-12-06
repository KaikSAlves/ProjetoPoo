package br.com.senacsp.projetopoo.crudapi.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table
public class Marca extends BaseEntity{

    @Column(columnDefinition = "mediumblob")
    private byte[] logo;

    public Marca(String nome, String descricao){
        super(nome, descricao);
    }

    @Override
    public String toString(){
        return getNome();
    }
}
