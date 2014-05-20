package com.tia.controller;

import java.util.Hashtable;

import javax.swing.JOptionPane;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.SalaDAO;
import com.tia.model.Sala;

/**
 * Classe responsável por controlar a gravação da Sala
 * @author Bruno
 * @since 17/05/2014
 */
public class CadastrarSalaController implements ValidarCadastro{

    StringBuilder msg = new StringBuilder();
        
    @Override
    public boolean validaEntradas(Hashtable<String, Object> parametros) {			
	if(parametros.get("nome").toString().isEmpty())
	    msg.append("-Nome da sala \n");
	return msg.toString().isEmpty();
    }

    @Override
    public Persistencia persistir(Hashtable<String, Object> parametros) {
	SalaDAO dao = new SalaDAO();
	Sala sala= new Sala();
	sala.setId_sala();
	sala.setNome(parametros.get("nome").toString());
	sala.setAndar((short)parametros.get("andar"));
	sala.setEquipamento((boolean)parametros.get("equipamento"));
	return dao.gravar(sala);
	
	
    }

    @Override
    public void validaPersistencia(Persistencia response) {
	if (response == null) {
	    JOptionPane.showMessageDialog(null,
		    "Os seguintes campos precisam ser verificados: \n "
			    + msg.toString(),"Campos não validados",JOptionPane.WARNING_MESSAGE );
	}else if (response == Persistencia.GRAVADO) {
	    JOptionPane.showMessageDialog(null,
		    "Sala gravada com sucesso", "Gravação com sucesso", JOptionPane.INFORMATION_MESSAGE);
	} else if (response == Persistencia.DUPLICADO) {
	    JOptionPane.showMessageDialog(null, "Sala já existe!","",JOptionPane.WARNING_MESSAGE);
	} else {
	    JOptionPane.showMessageDialog(null, "Erro na gravação!","Erro",JOptionPane.ERROR_MESSAGE);
	}
	
    }

}
