package br.com.senacsp.projetopoo.crudapi.dtos;

import br.com.senacsp.projetopoo.crudapi.model.Produto;

import java.util.List;

public record FornecedorDto(String nome, String descricao, String cnpj, String telefone, List<Produto> produtos) {
}
