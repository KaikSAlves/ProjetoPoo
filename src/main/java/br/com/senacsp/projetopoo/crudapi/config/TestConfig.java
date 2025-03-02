package br.com.senacsp.projetopoo.crudapi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.senacsp.projetopoo.crudapi.frames.FrameApplication;
import br.com.senacsp.projetopoo.crudapi.model.Fornecedor;
import br.com.senacsp.projetopoo.crudapi.model.Marca;
import br.com.senacsp.projetopoo.crudapi.model.Produto;
import br.com.senacsp.projetopoo.crudapi.repositories.FornecedorRepository;
import br.com.senacsp.projetopoo.crudapi.repositories.MarcaRepository;
import br.com.senacsp.projetopoo.crudapi.repositories.ProdutoRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        //adicinando fornecedores
        Fornecedor f1 = new Fornecedor("Kaik", "Pessoa", "981236498", "1190734793");
        Fornecedor f2 = new Fornecedor("Joao", "Pessoa", "578678567", "1196572355");
        Fornecedor f3 = new Fornecedor("Maria", "Pessoa", "890434457", "11987239789");

        fornecedorRepository.saveAll(Arrays.asList(f1,f2,f3));

        //adicionando Marcas
        Marca m1 = new Marca("Nike", "Empresa");
        Marca m2 = new Marca("Adidas", "Empresa");
        Marca m3 = new Marca("Oacley", "Empresa");

        marcaRepository.saveAll(Arrays.asList(m1, m2, m3));

        //adicionando Produtos
        Produto p1 = new Produto(1000, 10, 10, 10, 5, m1, f1, "Tenis", "Tenis preto");
        Produto p2 = new Produto(1200, 70, 20, 20, 5, m2, f2, "Taco", "Taco para esporte");
        Produto p3 = new Produto(900, 10, 20, 5, 10, m3, f3, "Oculos", "Oculos juliete");

    }
    
}
