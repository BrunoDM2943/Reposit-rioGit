package com.tia.controller.cadastro;

import javax.swing.JOptionPane;

import alocacaoDinamica.tabelaEspalhamento.TabelaEspalhamento;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.NoticiaDataAccess;
import com.tia.model.Noticia;

/**
 * Cadastra uma nova notícia no sistema
 * @author bruno.martins
 * @since 21/05/2014
 * @version 21/05/2014
 */
public class CadastrarNoticiaController implements ValidarCadastro{

	StringBuilder msg = new StringBuilder();
	
	@Override
	public boolean validaEntradas(TabelaEspalhamento<String, Object> parametros) {
		if(parametros.get("titulo").toString().isEmpty())
			msg.append("-A noticia deve conter um título \n");
		if(parametros.get("noticia").toString().isEmpty())
			msg.append("-A noticia deve conter um texto");
		return msg.toString().isEmpty();
	}

	@Override
	public Persistencia persistir(TabelaEspalhamento<String, Object> parametros) {
		NoticiaDataAccess daoNoticia = new NoticiaDataAccess();
		Persistencia response = null;
		Noticia noticia = new Noticia();
		noticia.setIdNoticia();
		noticia.setTitulo(parametros.get("titulo").toString());
		noticia.setTexto(parametros.get("noticia").toString());
		response = daoNoticia.gravar(noticia);		
		return response;
	}

	@Override
	public void validaPersistencia(Persistencia response) {
		if(response == Persistencia.GRAVADO)
		    JOptionPane.showMessageDialog(null, "A notícia foi gravada com sucesso!", "Gravado!", JOptionPane.INFORMATION_MESSAGE);
		if(response == Persistencia.ERRO)
		    JOptionPane.showMessageDialog(null, "Erro na gravação!", "Erro!", JOptionPane.ERROR_MESSAGE);
		if(response == Persistencia.DUPLICADO)
		    JOptionPane.showMessageDialog(null, "A notícia já existe!", "Duplicado!", JOptionPane.WARNING_MESSAGE);
		if(response == null)
		    JOptionPane.showMessageDialog(null, msg.toString(),"Erro!",JOptionPane.WARNING_MESSAGE);
		
	}
	

}
