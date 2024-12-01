package br.com.senacsp.projetopoo.crudapi.repository;

import br.com.senacsp.projetopoo.crudapi.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CategoriaRespository extends JpaRepository<Categoria, Integer> {
    public List<Categoria> findByNome(String nome);
}
