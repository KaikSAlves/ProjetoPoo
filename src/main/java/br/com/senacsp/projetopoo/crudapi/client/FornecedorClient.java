package br.com.senacsp.projetopoo.crudapi.client;

import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;
import org.springframework.web.bind.annotation.GetMapping;
import retrofit2.Call;

import java.util.List;

public interface FornecedorClient {
    @GetMapping("/fornecedores")
    Call<List<Fornecedor>> buscarTodosFornecedores();

    @GetMapping("/fornecedores/{id}")
    Call<List<Fornecedor>> buscarPorId();

    @GetMapping("/fornecedores/{nome}")
    Call<List<Fornecedor>> buscarPorNome();

    @GetMapping("/fornecedores/{cnpj}")
    Call<List<Fornecedor>> buscarPorCnpj();

    @GetMapping("/fornecedores/{id}")
    Call<Boolean> existePorId();

}
