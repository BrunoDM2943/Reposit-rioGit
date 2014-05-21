package com.tia.view.models.comboBox;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import com.tia.dao.StatusDataAccess;
import com.tia.model.Status;


/**
 * Classe responsável por modelar um ComboBox de Status
 * @author Bruno
 * @since 20/05/2014
 * @version 20/05/2014
 *
 */
public class StatusComboBoxModel extends AbstractListModel<Status> implements ComboBoxModel<Status> {
	
	private static final long	serialVersionUID	= 1L;
	Status[] status = carregaStatus();
	
	/**
	 * Popula os comboBox com o nome dos professores
	 * @return Vetor carregado
	 * @since 03/05/2014
	 * @author Bruno
	 * @param vector 
	 */
	public Status[] carregaStatus() {
		StatusDataAccess dao = new StatusDataAccess();
		return (Status[]) dao.lerTodos().toArray(Status.class);
	}
	
	
	Status selection = null;

	  public Status getElementAt(int index) {
	    return status[index];
	  }

	  public int getSize() {
	    return status.length;
	  }

	  public void setSelectedItem(Object e) {
	    selection = (Status) e; 
	  }
	  
	  
	  public Object getSelectedItem() {
	    return selection;
	  }
	}