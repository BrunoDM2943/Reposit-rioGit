package com.tia.view.models.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.tia.model.Docente;

/**
 * Classe responsável pelo controle do JTable de docentes
 * @author Bruno
 * @since 25/05/2014
 * @version 25/05/2014
 *
 */
public class DocenteTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 3735242513201969309L;

    private List<Docente> linhas;
    
    public DocenteTableModel() {
	linhas = new ArrayList<Docente>();
    }
    
    public DocenteTableModel(List<Docente> listaDocente) {
	linhas =  new ArrayList<Docente>(listaDocente);
    }
    
    private String[] colunas = new String[] {"Docente"};

    private static final int DOCENTE = 0;
    
    

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
	case DOCENTE:
	    return Docente.class;
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

	Docente dadosDocente = (Docente) linhas.get(rowIndex);
	switch (columnIndex) {
	case DOCENTE:
	    return dadosDocente.toString();
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
	fireTableCellUpdated(rowIndex, columnIndex); 
    }

    public Docente getRowAt(int row) {	
	return linhas.get(row);
    }


}
