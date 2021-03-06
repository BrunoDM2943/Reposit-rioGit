package com.tia.view.models.comboBox;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.dao.DocenteDataAccess;
import com.tia.dao.ProfessorDataAccess;
import com.tia.model.Curso;
import com.tia.model.Docente;
import com.tia.model.Professor;

/**
 * Classe responsável pelo controle do comboBox de professores
 * @author Bruno
 * @since 25/05/2014
 * @version 25/05/2014
 *
 */
public class ProfessorComboBoxModel extends AbstractListModel<Professor> implements	ComboBoxModel<Professor> {

    private static final long serialVersionUID = 1L;
    Professor[] professor;

    public ProfessorComboBoxModel(Curso curso) {
		DocenteDataAccess daoDocente = new DocenteDataAccess();
		Docente docente = null;
		ListaEncadeada<Docente> listaDocente = daoDocente.lerTodos();
		ListaEncadeada<Professor> listaProfessor = new ListaEncadeada<Professor>();
		ListaEncadeada<Curso> listaCurso;
		
		while(listaDocente.hasNext()){
			docente = listaDocente.next();
			listaCurso = docente.getCursos();
			while(listaCurso.hasNext()){
				if(listaCurso.next().equal(curso))
					listaProfessor.addFim(docente.getProf());
			}
		}
		
		professor = (Professor[]) listaProfessor.toArray(Professor.class);
		
	}

	public ProfessorComboBoxModel() {
		professor = carregaProfessores();
	}

	/**
     * Popula os comboBox com o nome dos professores
     * 
     * @return Vetor carregado
     * @since 03/05/2014
     * @author Bruno
     * @param vector
     */
    public Professor[] carregaProfessores() {
	ProfessorDataAccess dao = new ProfessorDataAccess();
	return (Professor[]) dao.lerTodos().toArray(Professor.class);
    }

    Professor selection = null;

    public Professor getElementAt(int index) {
	return professor[index];
    }

    public int getSize() {
	return professor.length;
    }

    public void setSelectedItem(Object e) {
	selection = (Professor) e;
    }

    public Object getSelectedItem() {
	return selection;
    }
}