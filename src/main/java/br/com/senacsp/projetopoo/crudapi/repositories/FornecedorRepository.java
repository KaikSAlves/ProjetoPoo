package br.com.senacsp.projetopoo.crudapi.repositories;

import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Integer> {
    public Optional<Fornecedor> findByCnpj(String cnpj);
}
