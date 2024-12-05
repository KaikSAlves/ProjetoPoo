package br.com.senacsp.projetopoo.crudapi.controllers;

import br.com.senacsp.projetopoo.crudapi.dtos.CategoriaDto;
import br.com.senacsp.projetopoo.crudapi.model.Categoria;
import br.com.senacsp.projetopoo.crudapi.services.CategoriaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody CategoriaDto dto){
        var categoria = new Categoria();
        BeanUtils.copyProperties(dto, categoria);

        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaService.salvar(categoria));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Integer id){
        Optional<Categoria> categoria = categoriaService.buscarPorId(id);

        if(categoria.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria nao encontrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(categoria.get());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity buscarPorNome(@PathVariable String nome){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.buscarPorNome(nome));
    }

    @GetMapping
    public ResponseEntity buscarTodasCategorias(){
        return ResponseEntity.status(HttpStatus.OK).body(categoriaService.buscarTodasCategorias());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarPorId(@PathVariable Integer id){
        Optional<Categoria> categoria = categoriaService.buscarPorId(id);

        if(categoria.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Categoria nao encontrada");
        }

        categoriaService.apagarPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body("Categoria excluida");
    }
}
