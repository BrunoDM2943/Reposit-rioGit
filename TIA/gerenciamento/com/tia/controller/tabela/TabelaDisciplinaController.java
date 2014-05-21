package com.tia.controller.tabela;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.controller.cadastro.CadastrarDisciplinaController;
import com.tia.controller.constantes.Persistencia;
import com.tia.dao.DisciplinaDataAccess;
import com.tia.model.Disciplina;
import com.tia.view.models.table.DisciplinaTableModel;

/**
 * Classe responsável por controlar e gerenciar a tabela de Professor
 * 
 * @author Bruno
 * 
 */
public class TabelaDisciplinaController {

    /**
     * Gera o modelo para a tabela de salas
     * 
     * @author Bruno
     * @since 17/05/2014
     * @return modelo
     */
    public DisciplinaTableModel geraTabela(DisciplinaTableModel model) {
	DisciplinaDataAccess dao = new DisciplinaDataAccess();
	LinkedList<Disciplina> lista = new LinkedList<Disciplina>();
	ListaEncadeada<Disciplina> listaDisciplina = dao.lerTodos();

	while (listaDisciplina.hasNext())
	    lista.add(listaDisciplina.next());

	model = new DisciplinaTableModel(lista);
	return model;
    }

    /**
     * Deleta uma Disciplina do registro
     * 
     * @author Bruno
     * @since 17/05/2014
     * @param Disciplina
     *            Disciplina a ser removida
     */
    public void deletarRegistro(Disciplina disc) {
	DisciplinaDataAccess dao = new DisciplinaDataAccess();
	Persistencia response = dao.deletar(disc);
	if (response == Persistencia.REMOVIDO) {
	    JOptionPane.showMessageDialog(null,
		    "Registro removido com sucesso!");
	} else {
	    JOptionPane.showMessageDialog(null, "Erro na remoção!", "Erro",
		    JOptionPane.ERROR_MESSAGE);
	}
    }

    // FIXME Quando atualizar, será que não vai gerar duplicidade
    public void atualizaDisciplina(int row, int column, Disciplina disc) {
	DisciplinaDataAccess dao = new DisciplinaDataAccess();
	Persistencia response;
	CadastrarDisciplinaController crtl;
	switch (column) {
	case 0:
	    disc.setNome(JOptionPane.showInputDialog(null,
		    "Digite um novo nome para a disciplina"));
	    response = dao.atualizar(disc);
	    crtl = new CadastrarDisciplinaController();
	    crtl.validaPersistencia(response);
	    break;	
	case 3:
	    try {
		disc.setSemestre(Integer.parseInt(JOptionPane
			.showInputDialog(null,
				"Digite o novo semestre para a disciplina")));
		response = dao.atualizar(disc);
		crtl = new CadastrarDisciplinaController();
		crtl.validaPersistencia(response);
	    } catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null,
			"Erro, o semestre precisa ser um número!!", "Erro",
			JOptionPane.ERROR_MESSAGE);
	    }

	    break;
	}

	crtl = null;
	response = null;
	disc = null;
	dao = null;

    }

}
