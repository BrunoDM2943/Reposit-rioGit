package com.tia.controller.cadastro;

import javax.swing.JOptionPane;

import alocacaoDinamica.tabelaEspalhamento.TabelaEspalhamento;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.LocalizacaoDataAccess;
import com.tia.model.Localizacao;
import com.tia.model.Professor;
import com.tia.model.Status;

/**
 * Classe responsável por gravar uma nova localização
 * @author bruno.martins
 * @since 21/05/2014
 * @version 21/05/2014
 *
 */
public class CadastrarLocalizacaoController implements ValidarCadastro {

	StringBuffer msg = new StringBuffer();
	
	@Override
	public boolean validaEntradas(TabelaEspalhamento<String, Object> parametros) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Persistencia persistir(TabelaEspalhamento<String, Object> parametros) {
		LocalizacaoDataAccess dao = new LocalizacaoDataAccess();
		Localizacao localizacao = new Localizacao();
		localizacao.setId_localizacao();
		localizacao.setProf((Professor)parametros.get("professor"));
		localizacao.setStatus((Status) parametros.get("status"));
		localizacao.setId_prof(localizacao.getProf().getId());
		localizacao.setIdStatus(localizacao.getStatus().getId());		
		return dao.gravar(localizacao);
	}

	@Override
	public void validaPersistencia(Persistencia response) {
		if(response == Persistencia.GRAVADO)
		    JOptionPane.showMessageDialog(null, "A localizacao foi gravado com sucesso!", "Gravado!", JOptionPane.INFORMATION_MESSAGE);
		if(response == Persistencia.ERRO)
		    JOptionPane.showMessageDialog(null, "Erro na gravação!", "Erro!", JOptionPane.ERROR_MESSAGE);
		if(response == Persistencia.DUPLICADO)
		    JOptionPane.showMessageDialog(null, "A localizacao já existe!", "Duplicado!", JOptionPane.WARNING_MESSAGE);
		if(response == null)
		    JOptionPane.showMessageDialog(null, msg.toString(),"Erro!",JOptionPane.WARNING_MESSAGE);

	}

}
