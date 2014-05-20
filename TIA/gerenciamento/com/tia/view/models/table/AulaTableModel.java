package com.tia.view.models.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.framework.Dia;
import com.tia.controller.constantes.Turno;
import com.tia.model.Aula;
import com.tia.model.Disciplina;
import com.tia.model.Professor;
import com.tia.model.Sala;

public class AulaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 3015180535339312881L;
	private ArrayList<Aula> linhas;

	public AulaTableModel(List<Aula> listaAulas) {
		linhas = new ArrayList<Aula>(listaAulas);
	}

	private String[] colunas = new String[] { "Curso", "Disciplina",
			"Professor", "Turno", "Dia da semana", "Sala", "Inicio", "Fim" };

	private static final int CURSO = 0;
	private static final int DISCIPLINA = 1;
	private static final int PROFESSOR = 2;
	private static final int TURNO = 3;
	private static final int DIA = 4;
	private static final int SALA = 5;
	private static final int INICIO = 6;
	private static final int FIM = 7;

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
		case CURSO:
			return String.class;
		case DISCIPLINA:
			return Disciplina.class;
		case PROFESSOR:
			return Professor.class;
		case TURNO:
			return Turno.class;
		case SALA:
			return Sala.class;
		case DIA:
			return Dia.class;
		case INICIO:
			return String.class;
		case FIM:
			return String.class;
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

		Aula dadosAula = linhas.get(rowIndex);
		switch (columnIndex) {
		case CURSO:
			return dadosAula.getDisc().getNomeCurso();
		case DISCIPLINA:
			return dadosAula.getDisc();
		case PROFESSOR:
			return dadosAula.getProf();
		case TURNO:
			return dadosAula.getTurno();
		case SALA:
			return dadosAula.getSala();
		case DIA:
			return dadosAula.getDia();
		case INICIO:
			return dadosAula.getIni().toString();
		case FIM:
			return dadosAula.getFim().toString();
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	public Aula getRowAt(int row) {
		return linhas.get(row);
	}

}
