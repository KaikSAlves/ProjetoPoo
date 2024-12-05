package br.com.senacsp.projetopoo.crudapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nome;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;

    public BaseEntity(String nome, String descricao){
        this.nome = nome;
        this.descricao = descricao;
    }

}

