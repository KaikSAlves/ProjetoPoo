package br.com.senacsp.projetopoo.crudapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository <T, ID> extends JpaRepository<T, ID> {
    public Optional<T> findByNome(String nome);
}

