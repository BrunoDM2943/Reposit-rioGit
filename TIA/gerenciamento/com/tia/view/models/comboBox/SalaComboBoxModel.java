package com.tia.view.models.comboBox;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.tia.dao.SalaDataAccess;
import com.tia.model.Sala;


public class SalaComboBoxModel extends AbstractListModel<Sala> implements ComboBoxModel<Sala> {
	
	private static final long	serialVersionUID	= 1L;
	Sala[] sala = carregaStatus();
	
	/**
	 * Popula os comboBox com o nome dos professores
	 * @return Vetor carregado
	 * @since 03/05/2014
	 * @author Bruno
	 * @param vector 
	 */
	public Sala[] carregaStatus() {
	    	SalaDataAccess dao = new SalaDataAccess();	    	
		return (Sala[]) dao.lerTodos().toArray(Sala.class);
	}
	
	
	Sala selection = null;

	  public Sala getElementAt(int index) {
	    return sala[index];
	  }

	  public int getSize() {
	    return sala.length;
	  }

	  public void setSelectedItem(Object e) {
	    selection = (Sala) e; 
	  }
	  
	  
	  public Object getSelectedItem() {
	    return selection;
	  }
	}