package br.com.senacsp.projetopoo.crudapi.controllers;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senacsp.projetopoo.crudapi.dtos.ProdutoDto;
import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;
import br.com.senacsp.projetopoo.crudapi.model.Marca;
import br.com.senacsp.projetopoo.crudapi.model.Produto;
import br.com.senacsp.projetopoo.crudapi.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController{

    @Autowired
    ProdutoService produtoService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody ProdutoDto dto){
        var produto = new Produto();
        BeanUtils.copyProperties(dto, produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.salvar(produto));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Integer id){
        Optional<Produto> produto = produtoService.buscarPorId(id);

        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(produto.get());
    }

    //TODO metodos com conflito (buscarPorFornecedor, buscarPorCategoria)
    @GetMapping("/fornecedor/{id}")
    public ResponseEntity buscarPorFornecedor(@PathVariable Integer id){
        var fornecedor = new Fornecedor();
        fornecedor.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscarPorFornecedor(fornecedor));
    }

    @GetMapping("/marca/{id}")
    public ResponseEntity buscarPorMarca(@PathVariable Integer id){
        var marca = new Marca();
        marca.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscarPorMarca(marca));
    }

    @GetMapping
    public ResponseEntity buscarTodosProdutos(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.buscarTodosProdutos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarPorId(@PathVariable Integer id){
        Optional<Produto> produto = produtoService.buscarPorId(id);

        if(produto.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado");
        }

        produtoService.apagarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto excluido");
    }
}
