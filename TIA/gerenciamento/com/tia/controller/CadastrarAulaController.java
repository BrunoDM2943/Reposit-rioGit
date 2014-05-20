package com.tia.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Hashtable;

import javax.swing.JOptionPane;

import com.framework.Dia;
import com.tia.controller.constantes.Persistencia;
import com.tia.controller.constantes.Turno;
import com.tia.dao.AulaDAO;
import com.tia.model.Aula;
import com.tia.model.Disciplina;
import com.tia.model.Professor;
import com.tia.model.Sala;

/**
 * Classe responsável por controlar a gravação
 * de uma nova aula
 * @author bruno.martins
 * @since 20/05/2014
 * @version 20/05/2014
 */
public class CadastrarAulaController implements ValidarCadastro{

	StringBuilder msg = new StringBuilder();
	Time ini = null;
	Time fim = null;
	@Override
	public boolean validaEntradas(Hashtable<String, Object> parametros) {
		
		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm");  
		java.util.Date data;
		try {
			data = formatador.parse(parametros.get("inicio").toString());
			ini = new Time(data.getTime());  
			data = formatador.parse(parametros.get("fim").toString());
			fim = new Time(data.getTime());
		} catch (ParseException e) { 
			e.printStackTrace();
		}  
		
		
		
		if(parametros.get("curso") == null)
			msg.append("-Pelo menos um curso deve ser selecionado \n");
		if (parametros.get("disciplina") == null)
			msg.append("-Pelo menos uma disciplina deve ser selecionada \n");
		if (parametros.get("professor") == null)
			msg.append("-Pelo menos um professor deve ser selecionado \n");
		if(parametros.get("turno") == null)
			msg.append("-Pelo menos um turno deve ser selecionado \n");
		if(parametros.get("sala") == null)
			msg.append("-Pelo menos uma sala deve ser selecionada \n");
		if(parametros.get("dia") == null)
			msg.append("-Pelo menos um dia deve ser selecionado \n");
		if(ini == null || fim == null){
			msg.append("-Uma aula deve ter início E fim \n");
		}else{
			if(ini.after(fim))
				msg.append("-O horário de início deve ser menor que o de fim \n");
		}
		return msg.toString().isEmpty();
	}

	@Override
	public Persistencia persistir(Hashtable<String, Object> parametros) {
		Aula aula = new Aula();
		aula.setIdAula();
		aula.setProf((Professor)parametros.get("professor"));
		aula.setSala((Sala) parametros.get("sala"));
		aula.setDisc((Disciplina) parametros.get("disciplina"));
		aula.setTurno((Turno) parametros.get("turno"));
		aula.setDia((Dia) parametros.get("dia"));
		aula.setIni(ini);
		aula.setFim(fim);
		
		AulaDAO dao = new AulaDAO();		
		return dao.gravar(aula);
	}

	@Override
	public void validaPersistencia(Persistencia response) {
		if(response == Persistencia.GRAVADO)
		    JOptionPane.showMessageDialog(null, "A aula foi gravado com sucesso!", "Gravado!", JOptionPane.INFORMATION_MESSAGE);
		if(response == Persistencia.ERRO)
		    JOptionPane.showMessageDialog(null, "Erro na gravação!", "Erro!", JOptionPane.ERROR_MESSAGE);
		if(response == Persistencia.DUPLICADO)
		    JOptionPane.showMessageDialog(null, "A aula já existe!", "Duplicado!", JOptionPane.WARNING_MESSAGE);
		if(response == null)
		    JOptionPane.showMessageDialog(null, msg.toString(),"Erro!",JOptionPane.WARNING_MESSAGE);
		
	}
	

}
