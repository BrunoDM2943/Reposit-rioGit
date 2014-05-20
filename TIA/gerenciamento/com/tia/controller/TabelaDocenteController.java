package com.tia.controller;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.DocenteDAO;
import com.tia.model.Docente;
import com.tia.view.models.table.DocenteTableModel;

/**
 * Classe responsável por controlar e gerenciar a tabela de Professor
 * 
 * @author Bruno
 * 
 */
public class TabelaDocenteController {

    /**
     * Gera o modelo para a tabela de docentes
     * 
     * @author Bruno
     * @since 17/05/2014
     * @return modelo
     */
    public DocenteTableModel geraTabela(DocenteTableModel model) {
	DocenteDAO dao = new DocenteDAO();
	LinkedList<Docente> lista = new LinkedList<Docente>();
	ListaEncadeada<Docente> listaDocente = dao.lerTodos();

	while (listaDocente.hasNext())
	    lista.add(listaDocente.next());

	model = new DocenteTableModel(lista);
	return model;
    }

    /**
     * Deleta uma Docente do registro
     * 
     * @author Bruno
     * @since 17/05/2014
     * @param Docente
     *            Docente a ser removida
     */
    public void deletarRegistro(Docente docente) {
	DocenteDAO dao = new DocenteDAO();
	Persistencia response = dao.deletar(docente);
	if (response == Persistencia.REMOVIDO) {
	    JOptionPane.showMessageDialog(null,
		    "Registro removido com sucesso!");
	} else {
	    JOptionPane.showMessageDialog(null, "Erro na remoção!", "Erro",
		    JOptionPane.ERROR_MESSAGE);
	}
    }

   
    

}
