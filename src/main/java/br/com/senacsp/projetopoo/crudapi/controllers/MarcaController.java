package br.com.senacsp.projetopoo.crudapi.controllers;

import br.com.senacsp.projetopoo.crudapi.dtos.MarcaDto;
import br.com.senacsp.projetopoo.crudapi.model.Marca;
import br.com.senacsp.projetopoo.crudapi.services.MarcaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    MarcaService marcaService;

    @PostMapping
    public ResponseEntity salvar(@RequestBody MarcaDto dto){
        var marca = new Marca();
        BeanUtils.copyProperties(dto, marca);
        return ResponseEntity.status(HttpStatus.CREATED).body(marcaService.salvar(marca));
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Integer id){
        Optional<Marca> marca = marcaService.buscarPorId(id);

        if(marca.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Marca nao encotrada");
        }

        return ResponseEntity.status(HttpStatus.OK).body(marca.get());
    }

    @GetMapping
    public ResponseEntity buscarTodasMarcas(){
        return ResponseEntity.status(HttpStatus.OK).body(marcaService.buscarTodasMarcas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagarPorId(@PathVariable Integer id){
        Optional<Marca> marca = marcaService.buscarPorId(id);

        if(marca.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Marca nao encontrada");
        }

        marcaService.apagarPorId(id);

        return ResponseEntity.status(HttpStatus.OK).body("Marca excluida");
    }
}
