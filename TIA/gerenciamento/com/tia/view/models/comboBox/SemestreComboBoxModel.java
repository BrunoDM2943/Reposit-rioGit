package com.tia.view.models.comboBox;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.tia.model.Curso;

@SuppressWarnings("rawtypes")
public class SemestreComboBoxModel extends AbstractListModel implements ComboBoxModel {

    private static final long serialVersionUID = -5882942305007462877L;
    int[] semestres;
    
    public SemestreComboBoxModel(Curso curso) {
	carregaSemestres(curso);
    }
    
    /**
     * Popula o comboBox com o semestre dos cursos
     * @author Bruno
     * @since 18/05/2014
     * @return vetor de cursos
     */
    private void carregaSemestres(Curso curso) {
	semestres = new int[curso.getQtdSemestres()];
	for(int i = 0; i < curso.getQtdSemestres();i++)
	    semestres[i] = i+1;
    }
    
    int selection = -1;

    @Override
    public Object getElementAt(int index) {
	return semestres[index];
    }
    
    @Override
    public int getSize() {
	return semestres.length;
    }

    @Override
    public void setSelectedItem(Object e) {
	selection = (int) e;
    }
    
    @Override
    public Object getSelectedItem() {
	return selection;
    }
}
