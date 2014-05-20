package com.tia.controller.tabela;

import java.util.LinkedList;

import javax.swing.JOptionPane;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.controller.cadastro.CadastrarSalaController;
import com.tia.controller.constantes.Persistencia;
import com.tia.dao.SalaDAO;
import com.tia.model.Sala;
import com.tia.view.models.table.SalaTableModel;

/**
 * Classe responsável por controlar e gerenciar a tabela de sala
 * @author Bruno
 *
 */
public class TabelaSalaController {
    
    /**
     * Gera o modelo para a tabela de salas
     * @author Bruno
     * @since 17/05/2014
     * @return modelo
     */
    public SalaTableModel geraTabela(SalaTableModel model) {
	SalaDAO dao = new SalaDAO();
	LinkedList<Sala> lista = new LinkedList<Sala>();
	ListaEncadeada<Sala> listaSala = dao.lerTodos();
	
	while(listaSala.hasNext())
	    lista.add(listaSala.next());
	
	model = new SalaTableModel(lista);
	return model;
    }

    /**
     * Deleta uma sala do registro
     * @author Bruno
     * @since 17/05/2014
     * @param sala Sala a ser removida
     */
    public void deletarRegistro(Sala sala) {
	SalaDAO dao = new SalaDAO();
	Persistencia response = dao.deletar(sala);
	if(response == Persistencia.REMOVIDO) {
	    JOptionPane.showMessageDialog(null, "Registro removido com sucesso!");
	}else {
	    JOptionPane.showMessageDialog(null, "Erro na remoção!", "Erro", JOptionPane.ERROR_MESSAGE);
	}
    }

    public void atualizarSala(int row, int column, Sala sala) {
	SalaDAO dao = new SalaDAO();
	Persistencia response;
	CadastrarSalaController crtl;
	switch (column) {
	case 0:
	    sala.setNome(JOptionPane.showInputDialog(null, "Digite um novo nome para a sala!"));
	    response = dao.gravar(sala);
	    crtl = new CadastrarSalaController();
	    crtl.validaPersistencia(response);
	    break;
	case 1:
	    sala.setAndar(Short.parseShort(JOptionPane.showInputDialog(null, "Digite um novo andar para a sala!")));
	    response = dao.atualizar(sala);	    
	    crtl = new CadastrarSalaController();
	    crtl.validaPersistencia(response);
	    break;
	case 2:	    
	    sala.setEquipamento(!sala.hasEquipamento());
	    response = dao.atualizar(sala);	    
	    crtl = new CadastrarSalaController();
	    crtl.validaPersistencia(response);
	    break;
	}
		
	
	crtl=null;
	response = null;
	sala = null;	
	dao =null;
	
    }

}
