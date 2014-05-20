package com.tabelas.tableModel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import com.tia.model.Localizacao;

public class LocalizacaoTableModel extends AbstractTableModel {
    private static final long serialVersionUID = 1L;

    private List<Localizacao> linhas;

    public LocalizacaoTableModel() {
	linhas = new ArrayList<>();
    }

    public LocalizacaoTableModel(List<Localizacao> listaLocalizacoes) {
	linhas = new ArrayList<>(listaLocalizacoes);
    }

    private String[] colunas = new String[] { "Professor", "Status", "Sala" };

    private static final int PROFESSOR = 0;
    private static final int STATUS = 1;
    private static final int SALA = 2;

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

    @Override
    public Class<?> getColumnClass(int columnIndex) {
	switch (columnIndex) {
	case PROFESSOR:
	    return String.class;
	case STATUS:
	    return String.class;
	case SALA:
	    return String.class;

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

	Localizacao dadosLocalizacao = linhas.get(rowIndex);
	switch (columnIndex) {
	case PROFESSOR:
	    return dadosLocalizacao.getNomeProfessor();
	case STATUS:
	    return dadosLocalizacao.getStatus();
	case SALA:
	    return dadosLocalizacao.getNomeSala();
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

	Localizacao dadosLocalizacao = linhas.get(rowIndex);
	switch (columnIndex) {
	case PROFESSOR:
	    dadosLocalizacao.setNomeProfessor((String) aValue);
	    break;
	case STATUS:
	    dadosLocalizacao.setStatus((String) aValue);
	    break;
	case SALA:
	    dadosLocalizacao.setNomeSala((String) aValue);
	    break;
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
	fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização
						     // da célula
    }

    

    @Override
    public void addTableModelListener(TableModelListener l) {
	// TODO Auto-generated method stub

    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
	// TODO Auto-generated method stub

    }
}
