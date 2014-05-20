package com.tia.view.models.comboBox;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.tia.dao.SalaDAO;
import com.tia.model.Sala;


@SuppressWarnings("rawtypes")
public class SalaComboBoxModel extends AbstractListModel implements ComboBoxModel {
	
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
	    	SalaDAO dao = new SalaDAO();	    	
		return (Sala[]) dao.lerTodos().toArray(Sala.class);
	}
	
	
	Sala selection = null;

	  public Object getElementAt(int index) {
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