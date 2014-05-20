package com.tia.controller;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.AulaDAO;
import com.tia.model.Aula;
import com.tia.view.models.table.AulaTableModel;

/**
 * Classe responsável por controlar e gerenciar a tabela de Aula
 * 
 * @author Bruno
 * 
 */
public class TabelaAulaController {

	/**
	 * Gera o modelo para a tabela de salas
	 * 
	 * @author Bruno
	 * @since 17/05/2014
	 * @return modelo
	 */
	public AulaTableModel geraTabela(AulaTableModel model) {
		AulaDAO dao = new AulaDAO();
		LinkedList<Aula> lista = new LinkedList<Aula>();
		ListaEncadeada<Aula> listaAula = dao.lerTodos();

		while (listaAula.hasNext())
			lista.add(listaAula.next());

		model = new AulaTableModel(lista);
		return model;
	}

	/**
	 * Deleta uma Aula do registro
	 * 
	 * @author Bruno
	 * @since 17/05/2014
	 * @param Aula
	 *            Aula a ser removida
	 */
	public void deletarRegistro(Aula aula) {
		AulaDAO dao = new AulaDAO();
		Persistencia response = dao.deletar(aula);
		if (response == Persistencia.REMOVIDO) {
			JOptionPane.showMessageDialog(null,
					"Registro removido com sucesso!");
		} else {
			JOptionPane.showMessageDialog(null, "Erro na remoção!", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
