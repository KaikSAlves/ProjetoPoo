package br.com.senacsp.projetopoo.crudapi.service;

import br.com.senacsp.projetopoo.crudapi.dtos.CategoriaDto;
import br.com.senacsp.projetopoo.crudapi.model.Categoria;
import br.com.senacsp.projetopoo.crudapi.repository.CategoriaRespository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    CategoriaRespository categoriaRespository;

    public Categoria salvar(Categoria categoria){
        return categoriaRespository.save(categoria);
    }

    public Optional<Categoria> buscarPorId(Integer id){
        return categoriaRespository.findById(id);
    }

    public List<Categoria> buscarPorNome(String nome){
        return categoriaRespository.findByNome(nome);
    }

    public List<Categoria> buscarTodasCategorias(){
        return categoriaRespository.findAll();
    }

    public void apagarPorId(Integer id){
        categoriaRespository.deleteById(id);
    }

}
