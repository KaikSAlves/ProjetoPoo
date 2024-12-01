package br.com.senacsp.projetopoo.crudapi.dtos;

import br.com.senacsp.projetopoo.crudapi.model.Categoria;
import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;
import br.com.senacsp.projetopoo.crudapi.model.Marca;

public record ProdutoDto(String nome, String descricao, Double preco, Integer altura, Integer larura, Integer profundidade,
                         Integer quantidadeEstoque, Marca marca, Categoria categoria, Fornecedor fornecedor) {

}
