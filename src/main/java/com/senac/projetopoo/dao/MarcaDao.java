package com.senac.projetopoo.dao;

import com.senac.projetopoo.model.Marca;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class MarcaDao implements InterfaceDao<Marca> {

    private EntityManager manager;

    public MarcaDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void inserir(Marca objeto) throws Exception {
        this.manager.persist(objeto);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public void alterar(Marca objeto) throws Exception {
        this.manager.merge(objeto);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public Marca buscar(long id) throws Exception {
        return this.manager.find(Marca.class, id);
    }

    @Override
    public void excluir(long id) throws Exception {
        Marca marca = buscar((int) id);
        this.manager.remove(marca);
        this.manager.getTransaction().begin();
        this.manager.getTransaction().commit();
    }

    @Override
    public List<Marca> listar() throws Exception {
        TypedQuery<Marca> query = this.manager.createQuery("select m from Marca m order by m.nome", Marca.class);
        return query.getResultList();
    }

}
