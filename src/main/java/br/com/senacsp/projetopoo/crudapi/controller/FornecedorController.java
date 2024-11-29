package br.com.senacsp.projetopoo.crudapi.controller;

import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;
import br.com.senacsp.projetopoo.crudapi.service.FornecedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    @Autowired
    FornecedorService fornecedorService;

    //Create & Update
    @PostMapping
    public Fornecedor salvar(@RequestBody Fornecedor fornecedor){
        return fornecedorService.salvar(fornecedor);
    }

    //Delete
    @DeleteMapping("/{id}")
    public void apagarPorId(@PathVariable Long id){
        fornecedorService.apagarPorId(id);
    }

    //Search
    @GetMapping("/{id}")
    public Optional<Fornecedor> buscarPorId(@PathVariable Long id){
        return fornecedorService.buscarPorId(id);
    }

    @GetMapping("/nome/{nome}")
    public Optional<Fornecedor> buscarPorNome(@PathVariable String nome){
        return fornecedorService.buscarPorNome(nome);
    }

    @GetMapping("/cnpj/{cnpj}")
    public Optional<Fornecedor> buscarPorCnpj(@PathVariable String cnpj){
        return fornecedorService.buscarPorCnpj(cnpj);
    }

    @GetMapping
    public List<Fornecedor> buscarTodosFornecedores(){
        return fornecedorService.buscarTodosFornecedores();
    }

}