package br.com.senacsp.projetopoo.crudapi.services;

import br.com.senacsp.projetopoo.crudapi.model.Marca;
import br.com.senacsp.projetopoo.crudapi.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    MarcaRepository marcaRepository;

    public Marca salvar(Marca marca ){
        return marcaRepository.save(marca);
    }

    public Optional<Marca> buscarPorId(Integer id){
        return marcaRepository.findById(id);
    }

    public List<Marca> buscarTodasMarcas(){
        return marcaRepository.findAll();
    }

    public void apagarPorId(Integer id){
        marcaRepository.deleteById(id);
    }

}
