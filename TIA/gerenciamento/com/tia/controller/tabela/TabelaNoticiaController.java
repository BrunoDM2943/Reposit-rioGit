package com.tia.controller.tabela;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.NoticiaDataAccess;
import com.tia.model.Noticia;
import com.tia.view.models.table.NoticiaTableModel;

/**
 * Classe responsável por controlar e gerenciar a tabela de Noticia
 * 
 * @author Bruno
 * 
 */
public class TabelaNoticiaController {

	/**
	 * Gera o modelo para a tabela de salas
	 * 
	 * @author Bruno
	 * @since 17/05/2014
	 * @return modelo
	 */
	public NoticiaTableModel geraTabela(NoticiaTableModel model) {
		NoticiaDataAccess dao = new NoticiaDataAccess();
		LinkedList<Noticia> lista = new LinkedList<Noticia>();
		ListaEncadeada<Noticia> listaNoticia = dao.lerTodos();

		while (listaNoticia.hasNext())
			lista.add(listaNoticia.next());

		model = new NoticiaTableModel(lista);
		return model;
	}

	/**
	 * Deleta uma Noticia do registro
	 * 
	 * @author Bruno
	 * @since 17/05/2014
	 * @param Noticia
	 *            Noticia a ser removida
	 */
	public void deletarRegistro(Noticia noticia) {
		NoticiaDataAccess dao = new NoticiaDataAccess();
		Persistencia response = dao.deletar(noticia);
		if (response == Persistencia.REMOVIDO) {
			JOptionPane.showMessageDialog(null,
					"Registro removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro na remoção!", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
