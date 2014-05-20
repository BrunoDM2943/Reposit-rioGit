package com.tia.view.models.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.tia.model.Disciplina;

public class DisciplinaTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 3015180535339312881L;
    private ArrayList<Disciplina> linhas;

    public DisciplinaTableModel(List<Disciplina> listaDisciplinas) {
	linhas = new ArrayList<Disciplina>(listaDisciplinas);
    }

    private String[] colunas = new String[] {"Nome", "Curso", "Semestre"};

    private static final int NOME = 0;
    private static final int CURSO = 1;
    private static final int SEMESTRE = 2;    

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
	case NOME:
	    return Disciplina.class;
	case CURSO:
	    return String.class;
	case SEMESTRE:
	    return int.class;	
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return true;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

	Disciplina dadosDisciplina = linhas.get(rowIndex);
	switch (columnIndex) {
	case NOME:
	    return dadosDisciplina.toString();
	case CURSO:
	    return dadosDisciplina.getNomeCurso();
	case SEMESTRE:
	    return dadosDisciplina.getSemestre();	
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

	Disciplina dadosDisciplina = linhas.get(rowIndex);
	switch (columnIndex) {
	case NOME:
	    dadosDisciplina = ((Disciplina) aValue);
	    break;
	case CURSO:
	    dadosDisciplina.setNomeCurso((String) aValue);
	    break;
	case SEMESTRE:
	    dadosDisciplina.setSemestre((int) aValue);
	    break;	
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
	fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização
						     // da célula
    }

    public Disciplina getRowAt(int row) {	
	return linhas.get(row);
    }

}
