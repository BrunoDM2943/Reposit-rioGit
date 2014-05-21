package com.tia.view.models.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.tia.model.Noticia;

public class NoticiaTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 3015180535339312881L;
	private ArrayList<Noticia> linhas;

	public NoticiaTableModel(List<Noticia> listaNoticias) {
		linhas = new ArrayList<Noticia>(listaNoticias);
	}

	private String[] colunas = new String[] { "Titulo", "Notícia",};

	private static final int TITULO = 0;
	private static final int NOTICIA = 1;

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
		case TITULO:
			return String.class;
		case NOTICIA:
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

		Noticia dadosNoticia = linhas.get(rowIndex);
		switch (columnIndex) {
		case TITULO:
			return dadosNoticia.getTitulo();
		case NOTICIA:
			return dadosNoticia.getTexto();		
		default:
			throw new IndexOutOfBoundsException("columnIndex out of bounds");
		}
	}

	public Noticia getRowAt(int row) {
		return linhas.get(row);
	}

}
