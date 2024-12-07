package com.senac.projetopoo.tablemodels;



import com.senac.projetopoo.model.Fornecedor;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class FornecedorTableModel extends AbstractTableModel {
    private static String[] cabecalho = {"Id", "Nome", "Cnpj"};
    private List<Fornecedor> fornecedores;

    public FornecedorTableModel(List<Fornecedor> fornecedores){
        this.fornecedores = fornecedores;
    }

    @Override
    public int getRowCount() {
        return fornecedores.size();
    }

    @Override
    public int getColumnCount() {
        return cabecalho.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fornecedor f = fornecedores.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return f.getId();
            case 1:
                return f.getNome();
            case 2:
                return f.getCnpj();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return cabecalho[column];
    }
}
