package com.tia.view.models.comboBox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JOptionPane;

import com.framework.Diretorios;
import com.framework.Mensagens;
import com.tia.model.Status;


@SuppressWarnings("rawtypes")
public class StatusComboBoxModel extends AbstractListModel implements ComboBoxModel {
	
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
		File dir = new File(Diretorios.STATUS.getPath());
		File[] files = dir.listFiles();
		Status [] listaStatus = null;
		if(files.length>0) {
		    listaStatus = new Status[files.length - 1];
			
			for(int i=0;i<files.length -1;i++) {
				try {
					System.out.println("Carregando ComboBox status");
					BufferedReader reader = new BufferedReader(new FileReader(files[i]));
					Status status = new Status();
					status.setStatus(reader.readLine());
					status.setId(Integer.parseInt(reader.readLine()));
					listaStatus[i] = status;
					reader.close();
					System.out.println("Status " + status.getStatus() + " carregado!");
				} catch (FileNotFoundException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					System.err.println(e.getMessage());
					e.printStackTrace();
				}
			}
			System.out.println("ComboBox status carregado!");
		}else {
			System.out.println("Não há status cadastrado!");
			JOptionPane.showMessageDialog(null,
					"Não há status parametrizado!",
					Mensagens.SEMSTATUS.toString(),
					JOptionPane.ERROR_MESSAGE);
			Status status = new Status();
			status.setStatus("Vazio");
			listaStatus = new Status[]{status};
			return listaStatus;
		}
		
		return listaStatus;
	}
	
	
	Status selection = null;

	  public Object getElementAt(int index) {
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