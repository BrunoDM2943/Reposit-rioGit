package com.tia.view.models.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.tia.model.Curso;

/**
 * Classe responsável pelo controle do JTable de cursos
 * @author Bruno
 * @since 25/05/2014
 * @version 25/05/2014
 *
 */
public class CursoTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 3015180535339312881L;
    private ArrayList<Curso> linhas;

    public CursoTableModel(List<Curso> listaCursos) {
	linhas = new ArrayList<Curso>(listaCursos);
    }

    private String[] colunas = new String[] {"Nome", "Codigo", "Matutino", "Vespertino","Noturno", "Quantidade de Semestres" };

    private static final int NOME = 0;
    private static final int CODIGO = 1;
    private static final int MATUTINO = 2;
    private static final int VESPERTINO = 3;
    private static final int NOTURNO = 4;    
    private static final int SEMESTRES = 5;

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
	    return Curso.class;
	case CODIGO:
	    return String.class;
	case MATUTINO:
	    return Boolean.class;
	case VESPERTINO:
	    return Boolean.class;
	case NOTURNO:
	    return Boolean.class;
	case SEMESTRES:
	    return int.class;
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
	return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

	Curso dadosCurso = linhas.get(rowIndex);
	switch (columnIndex) {
	case NOME:
	    return dadosCurso.getNome();
	case CODIGO:
	    return dadosCurso.getCodigo();
	/*
	 *     private static final int MATUTINO = 2;
    private static final int VESPERTINO = 3;
    private static final int NOTURNO = 4;  
	 */
	case MATUTINO:
		return dadosCurso.getTurnos().get("matutino");
	case VESPERTINO:
		return dadosCurso.getTurnos().get("vespertino");
	case NOTURNO:
		return dadosCurso.getTurnos().get("noturno");
	case SEMESTRES:
	    return dadosCurso.getQtdSemestres();
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

	Curso dadosCurso = linhas.get(rowIndex);
	switch (columnIndex) {
	case NOME:
	    dadosCurso.setNome((String) aValue);
	    break;
	case CODIGO:
	    dadosCurso.setCodigo((String) aValue);
	    break;
	case MATUTINO:
	    dadosCurso.setTurnos("matutino", (boolean) aValue);
	case VESPERTINO:
	    dadosCurso.setTurnos("vespertino", (boolean) aValue);
	case NOTURNO:
	    dadosCurso.setTurnos("noturno", (boolean) aValue);
	    break;
	case SEMESTRES:
	    dadosCurso.setQtdSemestres((int) aValue);
	    break;
	default:
	    throw new IndexOutOfBoundsException("columnIndex out of bounds");
	}
	fireTableCellUpdated(rowIndex, columnIndex); // Notifica a atualização
						     					// da célula
    }

    public Curso getRowAt(int row) {	
	return linhas.get(row);
    }

}
