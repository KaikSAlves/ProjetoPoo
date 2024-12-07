package com.senac.projetopoo.dao;
import com.senac.projetopoo.model.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class ProdutoDao implements InterfaceDao<Produto>{

    private EntityManager manager;

    public ProdutoDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void inserir(Produto objeto) throws Exception {
        this.manager.persist(objeto);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();

    }

    @Override
    public void alterar(Produto objeto) throws Exception {
        this.manager.merge(objeto);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public Produto buscar(long id) throws Exception {
        return this.manager.find(Produto.class, id);
    }

    @Override
    public void excluir(long id) throws Exception {
        Produto produto = buscar((int) id);
        this.manager.remove(produto);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public List<Produto> listar() throws Exception {
        TypedQuery<Produto> query = this.manager.createQuery("select p from Produto p order by p.nome", Produto.class);
        return query.getResultList();
    }

}
