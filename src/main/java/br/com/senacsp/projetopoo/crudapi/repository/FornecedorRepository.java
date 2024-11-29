package br.com.senacsp.projetopoo.crudapi.repository;

import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;

import java.util.Optional;

public interface FornecedorRepository extends BaseRepository<Fornecedor, Long> {
    public Optional<Fornecedor> findByCnpj(String cnpj);
}
