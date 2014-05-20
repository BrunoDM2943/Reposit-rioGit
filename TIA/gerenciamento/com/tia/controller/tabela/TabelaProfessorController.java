package com.tia.controller.tabela;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.controller.cadastro.CadastrarProfessorController;
import com.tia.controller.constantes.Persistencia;
import com.tia.dao.ProfessorDAO;
import com.tia.model.Professor;
import com.tia.view.models.table.ProfessorTableModel;

/**
 * Classe responsável por controlar e gerenciar a tabela de Professor
 * @author Bruno
 *
 */
public class TabelaProfessorController {
    
    /**
     * Gera o modelo para a tabela de salas
     * @author Bruno
     * @since 17/05/2014
     * @return modelo
     */
    public ProfessorTableModel geraTabela(ProfessorTableModel model) {
	ProfessorDAO dao = new ProfessorDAO();
	LinkedList<Professor> lista = new LinkedList<Professor>();
	ListaEncadeada<Professor> listaProfessor = dao.lerTodos();
	
	while(listaProfessor.hasNext())
	    lista.add(listaProfessor.next());
	
	model = new ProfessorTableModel(lista);
	return model;
    }

    /**
     * Deleta uma Professor do registro
     * @author Bruno
     * @since 17/05/2014
     * @param Professor Professor a ser removida
     */
    public void deletarRegistro(Professor prof) {
	ProfessorDAO dao = new ProfessorDAO();
	Persistencia response = dao.deletar(prof);
	if(response == Persistencia.REMOVIDO) {
	    JOptionPane.showMessageDialog(null, "Registro removido com sucesso!");
	}else {
	    JOptionPane.showMessageDialog(null, "Erro na remoção!", "Erro", JOptionPane.ERROR_MESSAGE);
	}
    }

    public void atualizarSala(int row, int column, Professor prof) {
	ProfessorDAO dao = new ProfessorDAO();
	Persistencia response;
	CadastrarProfessorController crtl;
	switch (column) {
	case 0:
	    prof.setNome(JOptionPane.showInputDialog(null, "Digite um novo nome para a Professor!"));
	    response = dao.gravar(prof);
	    crtl = new CadastrarProfessorController();
	    crtl.validaPersistencia(response);
	    break;	
	}
		
	
	crtl=null;
	response = null;
	prof = null;	
	dao =null;
	
    }

}
