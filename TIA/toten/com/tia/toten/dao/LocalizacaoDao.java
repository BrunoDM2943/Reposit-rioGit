package com.tia.toten.dao;

import java.util.Calendar;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.framework.Dia;
import com.framework.HtmlGenerator;
import com.tia.dao.AulaDAO;
import com.tia.dao.LocalizacaoDAO;
import com.tia.model.Aula;
import com.tia.model.Localizacao;

public class LocalizacaoDao {
	
	public String lerTodos(){
		String html  = "";
		LocalizacaoDAO daoLocalizacao = new LocalizacaoDAO();
		AulaDAO daoAula = new AulaDAO();
		ListaEncadeada<Localizacao> listaLocalizacao = new ListaEncadeada<Localizacao>();
		ListaEncadeada<Aula> listaAula = new ListaEncadeada<Aula>();
		ListaEncadeada<Object> listaGeral = new ListaEncadeada<Object>();
		Localizacao localizacao;
		Aula aula;
		listaLocalizacao = daoLocalizacao.lerTodos();
		while(listaLocalizacao.hasNext()){
			localizacao = listaLocalizacao.next();
			if(localizacao.getStatus().getStatus().equalsIgnoreCase("Presente")){
				aula = new Aula();				
				listaAula = daoAula.ler(Dia.getDia());
				while(listaAula.hasNext()){
					aula = listaAula.next();
					if(aula.getProf().equals(localizacao.getProf()) &&
							aula.getIni().getTime() > (Calendar.getInstance().getTime().getTime()) && aula.getFim().getTime() < (Calendar.getInstance().getTime().getTime())){
						listaGeral.addFim(aula);
					}
				}
			}else{
				listaGeral.addFim(localizacao);
			}
		}
		HtmlGenerator htmlGenerator = new HtmlGenerator();
		html = htmlGenerator.gerarHtml(listaGeral);		
		return html;
	}
}
