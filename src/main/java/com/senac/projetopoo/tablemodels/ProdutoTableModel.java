/*
 
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template*/
package com.senac.projetopoo.tablemodels;

import com.senac.projetopoo.model.Produto;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *

 @author win*/
public class ProdutoTableModel extends AbstractTableModel{

    private static String[] cabecalho = {"Id", "Nome", "Qntde", "Marca", "Fornecedor", "Preço"};
    private List<Produto> produtos;

    public ProdutoTableModel(List<Produto> produtos){
        this.produtos = produtos;
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return cabecalho.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto p = produtos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return p.getId();
            case 1:
                return p.getNome();
            case 2:
                return p.getQuantidadeEstoque();
            case 3:
                return p.getMarca().getNome();
            case 4:
                return  p.getFornecedor().getNome();
            case 5:
                return p.getPreco();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return cabecalho[column];
    }


}