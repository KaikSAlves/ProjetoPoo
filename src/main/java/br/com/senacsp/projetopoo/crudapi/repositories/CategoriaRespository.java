package br.com.senacsp.projetopoo.crudapi.repositories;

import br.com.senacsp.projetopoo.crudapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRespository extends JpaRepository<Categoria, Integer> {
    public List<Categoria> findByNome(String nome);
}
