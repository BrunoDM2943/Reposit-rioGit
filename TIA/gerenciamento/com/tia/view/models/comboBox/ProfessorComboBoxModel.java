package com.tia.view.models.comboBox;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.tia.dao.ProfessorDAO;
import com.tia.model.Professor;

@SuppressWarnings("rawtypes")
public class ProfessorComboBoxModel extends AbstractListModel implements
	ComboBoxModel {

    private static final long serialVersionUID = 1L;
    Professor[] professor = carregaProfessores();

    /**
     * Popula os comboBox com o nome dos professores
     * 
     * @return Vetor carregado
     * @since 03/05/2014
     * @author Bruno
     * @param vector
     */
    public Professor[] carregaProfessores() {
	ProfessorDAO dao = new ProfessorDAO();
	return (Professor[]) dao.lerTodos().toArray(Professor.class);
    }

    Professor selection = null;

    public Object getElementAt(int index) {
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