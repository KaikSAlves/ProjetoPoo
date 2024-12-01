package br.com.senacsp.projetopoo.crudapi.service;

import br.com.senacsp.projetopoo.crudapi.model.Categoria;
import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;
import br.com.senacsp.projetopoo.crudapi.model.Marca;
import br.com.senacsp.projetopoo.crudapi.model.Produto;
import br.com.senacsp.projetopoo.crudapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public Produto salvar(Produto entity){
        return produtoRepository.save(entity);
    }

    public void apagarPorId(Integer aLong) {
        produtoRepository.deleteById(aLong);
    }

    public Optional<Produto> buscarPorId(Integer aLong){
        return produtoRepository.findById(aLong);
    }

    public List<Produto> buscarPorFornecedor(Fornecedor fornecedor){
        return produtoRepository.findByFornecedor(fornecedor);
    }

    public List<Produto> buscarPorCategoria(Categoria categoria){
        return produtoRepository.findByCategoria(categoria);
    }

    public List<Produto> buscarPorMarca(Marca marca){
        return produtoRepository.findByMarca(marca);
    }

    public Iterable<Produto> buscarTodosPorId(Iterable<Integer> longs){
        return produtoRepository.findAllById(longs);
    }

    public boolean existePorId(Integer aLong){
        return produtoRepository.existsById(aLong);
    }

    public List<Produto> buscarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public long contarQuantidade(){
        return produtoRepository.count();
    }


}
