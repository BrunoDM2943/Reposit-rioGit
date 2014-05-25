package com.tia.view.models.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.tia.model.Sala;

/**
 * Classe responsável pelo jTable de salas
 * @author Bruno Damasceno Martins
 * @since 26/12/2013
 */

public class SalaTableModel extends AbstractTableModel {

   private static final long serialVersionUID = 1L;

	private List<Sala> linhas;

    public SalaTableModel() {
        linhas = new ArrayList<>();
    }

    public SalaTableModel(List<Sala> salas) {
        linhas = new ArrayList<>(salas);
    }

    private String[] colunas = new String[]{
        "Sala", "Andar", "Possui equipamento?"
    };

    private static final int SALA = 0;
    private static final int ANDAR = 1;
    private static final int EQUIPAMENTO = 2;

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }
    
    public Sala getRowAt(int row) {
	return linhas.get(row);
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case SALA:
                return String.class;
            case ANDAR:
                return Short.class;
            case EQUIPAMENTO:
                return Boolean.class;           
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // apenas o campo "ATIVO" será editável
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Sala sala = linhas.get(rowIndex);
        switch (columnIndex) {
            case SALA:
                return sala.getNome();
            case ANDAR:
                return sala.getAndar();
            case EQUIPAMENTO:
                return sala.hasEquipamento();     
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
	public void setValueAt(Object e, int rowIndex, int columnIndex) {

        Sala sala = linhas.get(rowIndex);
        switch (columnIndex) {
            case SALA:
                sala.setNome((String) e);
                break;
            case ANDAR:
                sala.setAndar((short) e);
                break;
            case EQUIPAMENTO:
                sala.setEquipamento((boolean) e);
                break;          
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização da célula
    }
    
     public void removeAluguel(int indiceLinha) {
        // Remove o registro.
        linhas.remove(indiceLinha);

        //Notifica a mudança.
        fireTableRowsDeleted(indiceLinha, indiceLinha);
    }
    
}
