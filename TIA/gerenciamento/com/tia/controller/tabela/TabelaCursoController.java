package com.tia.controller.tabela;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.controller.cadastro.CadastrarCursoController;
import com.tia.controller.constantes.Persistencia;
import com.tia.dao.CursoDataAccess;
import com.tia.model.Curso;
import com.tia.view.models.table.CursoTableModel;

/**
 * Classe responsável por controlar e gerenciar a tabela de Professor
 * 
 * @author Bruno
 * 
 */
public class TabelaCursoController {

    /**
     * Gera o modelo para a tabela de salas
     * 
     * @author Bruno
     * @since 17/05/2014
     * @return modelo
     */
    public CursoTableModel geraTabela(CursoTableModel model) {
	CursoDataAccess dao = new CursoDataAccess();
	LinkedList<Curso> lista = new LinkedList<Curso>();
	ListaEncadeada<Curso> listaCurso = dao.lerTodos();

	while (listaCurso.hasNext())
	    lista.add(listaCurso.next());

	model = new CursoTableModel(lista);
	return model;
    }

    /**
     * Deleta uma Curso do registro
     * 
     * @author Bruno
     * @since 17/05/2014
     * @param Curso
     *            Curso a ser removida
     */
    public void deletarRegistro(Curso curso) {
	CursoDataAccess dao = new CursoDataAccess();
	Persistencia response = dao.deletar(curso);
	if (response == Persistencia.REMOVIDO) {
	    JOptionPane.showMessageDialog(null,
		    "Registro removido com sucesso!");
	} else {
	    JOptionPane.showMessageDialog(null, "Erro na remoção!", "Erro",
		    JOptionPane.ERROR_MESSAGE);
	}
    }

    // FIXME Quando atualizar, será que não vai gerar duplicidade
    public void atualizaCurso(int row, int column, Curso curso) {
	CursoDataAccess dao = new CursoDataAccess();
	Persistencia response;
	CadastrarCursoController crtl;
	switch (column) {
	case 0:
	    curso.setNome(JOptionPane.showInputDialog(null,
		    "Digite um novo nome para o curso"));
	    response = dao.atualizar(curso);
	    crtl = new CadastrarCursoController();
	    crtl.validaPersistencia(response);
	    break;
	case 1:
	    curso.setCodigo(JOptionPane.showInputDialog(null,
		    "Digite um novo codigo para o curso"));
	    response = dao.atualizar(curso);
	    crtl = new CadastrarCursoController();
	    crtl.validaPersistencia(response);
	    break;
	case 2:
		curso.setTurnos("matutino", !curso.getTurnos().get("matutino"));
	    response = dao.atualizar(curso);
	    crtl = new CadastrarCursoController();
	    crtl.validaPersistencia(response);
	    break;
	case 3:
		curso.setTurnos("vespertino", !curso.getTurnos().get("vespertino"));
	    response = dao.atualizar(curso);
	    crtl = new CadastrarCursoController();
	    crtl.validaPersistencia(response);
	    break;
	case 4:
		curso.setTurnos("noturno", !curso.getTurnos().get("noturno"));
	    response = dao.atualizar(curso);
	    crtl = new CadastrarCursoController();
	    crtl.validaPersistencia(response);
	    break;    	
	case 5:
	    try {
		curso.setQtdSemestres(Integer.parseInt(JOptionPane
			.showInputDialog(null,
				"Digite uma nova quantiade de semestres para o curso")));
		response = dao.atualizar(curso);
		crtl = new CadastrarCursoController();
		crtl.validaPersistencia(response);
	    } catch (NumberFormatException e) {
		JOptionPane.showMessageDialog(null,
			"A quantidade de semestres não pode ser uma letra!!", "Erro",
			JOptionPane.ERROR_MESSAGE);
	    }

	    break;
	}

	crtl = null;
	response = null;
	curso = null;
	dao = null;

    }

}
