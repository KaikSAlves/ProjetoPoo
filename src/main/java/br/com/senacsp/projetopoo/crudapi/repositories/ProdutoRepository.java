package br.com.senacsp.projetopoo.crudapi.repositories;

import br.com.senacsp.projetopoo.crudapi.model.Categoria;
import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;
import br.com.senacsp.projetopoo.crudapi.model.Marca;
import br.com.senacsp.projetopoo.crudapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    public List<Produto> findByFornecedor(Fornecedor fornecedor);
    public List<Produto> findByCategoria(Categoria categoria);
    public List<Produto> findByMarca(Marca marca);
}
