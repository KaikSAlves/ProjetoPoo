package br.com.senacsp.projetopoo.crudapi.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
