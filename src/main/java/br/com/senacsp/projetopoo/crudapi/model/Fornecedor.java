package br.com.senacsp.projetopoo.crudapi.model;

import jakarta.persistence.*;
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

}

