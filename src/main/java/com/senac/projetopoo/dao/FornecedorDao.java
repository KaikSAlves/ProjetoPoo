package com.senac.projetopoo.dao;

import com.senac.projetopoo.model.Fornecedor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class FornecedorDao implements InterfaceDao<Fornecedor>{
    private EntityManager manager;

    public FornecedorDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void inserir(Fornecedor objeto) throws Exception {
        this.manager.persist(objeto);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();

    }

    @Override
    public void alterar(Fornecedor objeto) throws Exception {
        this.manager.merge(objeto);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public Fornecedor buscar(long id) throws Exception {
        return this.manager.find(Fornecedor.class, id);
    }

    @Override
    public void excluir(long id) throws Exception {
        Fornecedor fornecedor = buscar(id);
        this.manager.remove(fornecedor);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public List<Fornecedor> listar() throws Exception {
        TypedQuery<Fornecedor> query = this.manager.createQuery("select f from Fornecedor f order by f.nome", Fornecedor.class);
        return query.getResultList();
    }

}
