package br.com.senacsp.projetopoo.crudapi.tablemodels;

import br.com.senacsp.projetopoo.crudapi.model.Marca;
import br.com.senacsp.projetopoo.crudapi.model.Produto;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class MarcaTableModel extends AbstractTableModel {
    private static String[] cabecalho = {"Id", "Nome"};
    private List<Marca> marcas;

    public MarcaTableModel(List<Marca> marcas){
        this.marcas = marcas;
    }

    @Override
    public int getRowCount() {
        return marcas.size();
    }

    @Override
    public int getColumnCount() {
        return cabecalho.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Marca m = marcas.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return m.getId();
            case 1:
                return m.getNome();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        return cabecalho[column];
    }
}
