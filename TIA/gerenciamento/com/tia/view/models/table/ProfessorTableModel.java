package com.tia.view.models.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.tia.model.Professor;

public class ProfessorTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 3735242513201969309L;

    private List<Professor> linhas;
    
    public ProfessorTableModel() {
	linhas = new ArrayList<Professor>();
    }
    
    public ProfessorTableModel(List<Professor> listaProfessor) {
	linhas =  new ArrayList<Professor>(listaProfessor);
    }
    
    private String[] colunas = new String[] {"Nome"};

    private static final int PROFESSOR = 0;
    
    

    @Override
    public int getColumnCount() {	
	return colunas.length;
    }

    @Override
    public int getRowCount() {	
	return linhas.size();
    }
   
    @Override
    public String getColumnName(int columnIndex) {
	return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
	switch (columnIndex) {
	case PROFESSOR:
	    return Professor.class;
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

	Professor dadosProfessor = (Professor) linhas.get(rowIndex);
	switch (columnIndex) {
	case PROFESSOR:
	    return dadosProfessor.getNome();
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	fireTableCellUpdated(rowIndex, columnIndex); 
    }

    public Professor getRowAt(int row) {	
	return linhas.get(row);
    }


}
