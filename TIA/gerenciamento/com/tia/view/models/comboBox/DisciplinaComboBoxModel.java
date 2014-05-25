package com.tia.view.models.comboBox;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import alocacaoDinamica.fila.Fila;
import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.dao.DisciplinaDataAccess;
import com.tia.model.Curso;
import com.tia.model.Disciplina;

/**
 * Classe responsável pelo controle do comboBox de disciplinas
 * @author Bruno
 * @since 25/05/2014
 * @version 25/05/2014
 *
 */
public class DisciplinaComboBoxModel extends AbstractListModel<Disciplina> implements ComboBoxModel<Disciplina> {
	
	private static final long serialVersionUID = -1161680969380234934L;
	Disciplina[] disciplinas;
	Disciplina selection = null;
	public DisciplinaComboBoxModel(Curso curso) {
		DisciplinaDataAccess dao = new DisciplinaDataAccess();
		ListaEncadeada<Disciplina> lista = dao.lerTodos();
		Fila<Disciplina> fila = new Fila<Disciplina>();
		while(lista.hasNext()){
			Disciplina disc = lista.next();
			if(disc.getIdCurso() == curso.getIdCurso())
				fila.add(disc);
		}
		// TODO Melhorar isso!
		disciplinas = new Disciplina[fila.size];
		for(int i=0; i <disciplinas.length;i++){
			disciplinas[i] = fila.getInicio();
			fila.rem();
		}
		
	}

	
	@Override
	public Disciplina getElementAt(int arg0) {		
		return disciplinas[arg0];
	}

	@Override
	public int getSize() {		
		return disciplinas.length;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		selection =  (Disciplina) anItem;
		
	}

}
