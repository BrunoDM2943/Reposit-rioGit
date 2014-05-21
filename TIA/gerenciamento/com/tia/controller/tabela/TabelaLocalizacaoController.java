package com.tia.controller.tabela;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.LocalizacaoDAO;
import com.tia.model.Localizacao;
import com.tia.view.models.table.LocalizacaoTableModel;

/**
 * Classe responsável por controlar e gerenciar a tabela de Localizacao
 * 
 * @author Bruno
 * 
 */
public class TabelaLocalizacaoController {

	/**
	 * Gera o modelo para a tabela de salas
	 * 
	 * @author Bruno
	 * @since 17/05/2014
	 * @return modelo
	 */
	public LocalizacaoTableModel geraTabela(LocalizacaoTableModel model) {
		LocalizacaoDAO dao = new LocalizacaoDAO();
		LinkedList<Localizacao> lista = new LinkedList<Localizacao>();
		ListaEncadeada<Localizacao> listaLocalizacao = dao.lerTodos();

		while (listaLocalizacao.hasNext())
			lista.add(listaLocalizacao.next());

		model = new LocalizacaoTableModel(lista);
		return model;
	}

	/**
	 * Deleta uma Localizacao do registro
	 * 
	 * @author Bruno
	 * @since 17/05/2014
	 * @param Localizacao
	 *            Localizacao a ser removida
	 */
	public void deletarRegistro(Localizacao localizacao) {
		LocalizacaoDAO dao = new LocalizacaoDAO();
		Persistencia response = dao.deletar(localizacao);
		if (response == Persistencia.REMOVIDO) {
			JOptionPane.showMessageDialog(null,
					"Registro removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro na remoção!", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
