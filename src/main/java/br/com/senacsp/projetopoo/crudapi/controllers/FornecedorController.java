package br.com.senacsp.projetopoo.crudapi.controllers;

import br.com.senacsp.projetopoo.crudapi.dtos.FornecedorDto;
import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;
import br.com.senacsp.projetopoo.crudapi.services.FornecedorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/fornecedores")
public class FornecedorController {

    @Autowired
    FornecedorService fornecedorService;

    //Create & Update
    @PostMapping
    //boas praticas de programacao utilizar um objeto Dto invez de Entity para realizar um Post
    public ResponseEntity salvar(@RequestBody FornecedorDto dto){
        var fornecedor = new Fornecedor();
        BeanUtils.copyProperties(dto, fornecedor);
        return ResponseEntity.status(HttpStatus.CREATED).body(fornecedorService.salvar(fornecedor));

    }

    //Delete
    @DeleteMapping("/{id}")

    public ResponseEntity apagarPorId(@PathVariable Integer id){
        Optional<Fornecedor> fornecedor = fornecedorService.buscarPorId(id);

        if(fornecedor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor nao encontrado");
        }

        fornecedorService.apagarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body("Fornecedor excluído");
    }

    //Search
    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Integer id){
        Optional<Fornecedor> fornecedor = fornecedorService.buscarPorId(id);

        if(fornecedor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor nao encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(fornecedor.get());
    }

    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity buscarPorCnpj(@PathVariable String cnpj){
        Optional<Fornecedor> fornecedor = fornecedorService.buscarPorCnpj(cnpj);

        if(fornecedor.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fornecedor nao encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(fornecedor.get());
    }

    @GetMapping
    public ResponseEntity buscarTodosFornecedores(){
        return ResponseEntity.status(HttpStatus.OK).body(fornecedorService.buscarTodosFornecedores());
    }

}