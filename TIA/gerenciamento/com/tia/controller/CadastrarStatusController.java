package com.tia.controller;

import java.util.Hashtable;

import javax.swing.JOptionPane;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.StatusDAO;
import com.tia.model.Status;

public class CadastrarStatusController implements ValidarCadastro{

	StringBuilder msg = new StringBuilder();
	
	@Override
	public boolean validaEntradas(Hashtable<String, Object> parametros) {
		if(parametros.get("status").toString().isEmpty())
			msg.append("-O Status não pode ser nulo!");
		return msg.toString().isEmpty();
	}

	@Override
	public Persistencia persistir(Hashtable<String, Object> parametros) {
		StatusDAO dao = new StatusDAO();
		Status status = new Status();
		status.setId();
		status.setStatus(parametros.get("status").toString());
		return dao.gravar(status);
	}

	@Override
	public void validaPersistencia(Persistencia response) {
		if(response == Persistencia.GRAVADO)
		    JOptionPane.showMessageDialog(null, "O status foi gravado com sucesso!", "Gravado!", JOptionPane.INFORMATION_MESSAGE);
		if(response == Persistencia.ERRO)
		    JOptionPane.showMessageDialog(null, "Erro na gravação!", "Erro!", JOptionPane.ERROR_MESSAGE);
		if(response == Persistencia.DUPLICADO)
		    JOptionPane.showMessageDialog(null, "O status já existe!", "Duplicado!", JOptionPane.WARNING_MESSAGE);
		if(response == null)
		    JOptionPane.showMessageDialog(null, msg.toString(),"Erro!",JOptionPane.WARNING_MESSAGE);
		
	}

}
