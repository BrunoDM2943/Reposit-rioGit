package com.tia.view.models.comboBox;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.tia.dao.CursoDataAccess;
import com.tia.model.Curso;

/**
 * Classe responsável pelo controle do comboBox de Cursos
 * @author Bruno
 * @since 25/05/2014
 * @version 25/05/2014
 *
 */
@SuppressWarnings("rawtypes")
public class CursoComboBoxModel extends AbstractListModel implements ComboBoxModel  {

    private static final long serialVersionUID = -1610166362553396803L;
    Curso[] cursos = carregaCursos();
    
    /**
     * Popula o comboBox com o nome dos cursos
     * @author Bruno
     * @since 18/05/2014
     * @return vetor de cursos
     */
    public Curso[] carregaCursos() {
	CursoDataAccess dao = new CursoDataAccess();
	return (Curso[]) dao.lerTodos().toArray(Curso.class);
    }
    
    Curso selection = null;
    
    public Curso getElementAt(int index) {
	return cursos[index];
    }
    
    @Override
    public int getSize() {
	return cursos.length;
    }

    @Override
    public void setSelectedItem(Object anItem) {
	selection =  (Curso) anItem;
    }
    
    @Override
    public Object getSelectedItem() {
	return selection;
    }

}
