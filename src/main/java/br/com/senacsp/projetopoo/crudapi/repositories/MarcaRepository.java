package br.com.senacsp.projetopoo.crudapi.repositories;

import br.com.senacsp.projetopoo.crudapi.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Integer> {
}
