package com.senac.projetopoo.dao;



import com.senac.projetopoo.model.Marca;
import java.util.List;


public interface InterfaceDao <T> {
    void inserir(T objeto) throws Exception;
    void alterar(T objeto) throws Exception;
    T buscar(long id) throws Exception;
    void excluir(long id) throws Exception;
    List<T> listar() throws Exception;
}