package br.com.senacsp.projetopoo.crudapi.service;

import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;
import br.com.senacsp.projetopoo.crudapi.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    FornecedorRepository fornecedorRepository;

    //Metodos para salvar e alterar
    public Fornecedor salvar(Fornecedor entity) {
        return fornecedorRepository.save(entity);
    }

    //Metodos para apagar
    public void apagarPorId(Long aLong) {
        fornecedorRepository.deleteById(aLong);
    }

    //Metodos de busca
    public Optional<Fornecedor> buscarPorCnpj(String cnpj) {
        return fornecedorRepository.findByCnpj(cnpj);
    }

    public Optional<Fornecedor> buscarPorNome(String nome) {
        return fornecedorRepository.findByNome(nome);
    }

    public Optional<Fornecedor> buscarPorId(Long aLong) {
        return fornecedorRepository.findById(aLong);
    }

    public Iterable<Fornecedor> buscarTodosPorId(Iterable<Long> longs) {
        return fornecedorRepository.findAllById(longs);
    }

    public boolean existePorId(Long aLong) {
        return fornecedorRepository.existsById(aLong);
    }

    public List<Fornecedor> buscarTodosFornecedores() {
        return fornecedorRepository.findAll();
    }

    //Metodos de quantificação
    public long contarQuantidade() {
        return fornecedorRepository.count();
    }
}