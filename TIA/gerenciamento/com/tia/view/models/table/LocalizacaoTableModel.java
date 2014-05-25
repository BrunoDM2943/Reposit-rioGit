package com.tia.view.models.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.tia.model.Localizacao;
import com.tia.model.Professor;
import com.tia.model.Status;

/**
 * Classe responsável pelo controle do JTable de localizacoes
 * @author Bruno
 * @since 25/05/2014
 * @version 25/05/2014
 *
 */
public class LocalizacaoTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 3015180535339312881L;
	private ArrayList<Localizacao> linhas;

	public LocalizacaoTableModel(List<Localizacao> listaLocalizacaos) {
		linhas = new ArrayList<Localizacao>(listaLocalizacaos);
	}

	private String[] colunas = new String[] { "Professor", "Status"};


	private static final int PROFESSOR = 0;
	private static final int STATUS = 1;

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
			return Professor.class;
		case STATUS:
			return Status.class;		
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

		Localizacao dadosLocalizacao = linhas.get(rowIndex);
		switch (columnIndex) {

		case PROFESSOR:
			return dadosLocalizacao.getProf();
		case STATUS:
			return dadosLocalizacao.getStatus();
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	public Localizacao getRowAt(int row) {
		return linhas.get(row);
	}

}
