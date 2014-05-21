package com.tia.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.framework.Dia;
import com.framework.Diretorios;
import com.framework.HtmlGenerator;
import com.tia.controller.constantes.Persistencia;
import com.tia.model.Aula;
import com.tia.model.Localizacao;

public class LocalizacaoDataAccess implements DataAccessObject<Localizacao> {

	ProfessorDataAccess daoProf = new ProfessorDataAccess();
	StatusDataAccess daoStatus = new StatusDataAccess();

	@Override
	public Persistencia gravar(Localizacao e) {
		String path = Diretorios.LOCALIZACAO.getArquivo(e.getId_localizacao());
		BufferedWriter writer;
		FileWriter file;
		try {
			file = new FileWriter(path);
			writer = new BufferedWriter(file);
			writer.write(String.valueOf(e.getId_localizacao()));
			writer.newLine();
			writer.write(String.valueOf(e.getId_prof()));
			writer.newLine();
			writer.write(String.valueOf(e.getIdStatus()));
			writer.close();
			return Persistencia.GRAVADO;
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
		}
		return Persistencia.ERRO;
	}

	@Override
	public ListaEncadeada<Localizacao> lerTodos() {
		File[] arquivos = new File(Diretorios.LOCALIZACAO.toString())
				.listFiles();
		FileReader file;
		BufferedReader reader;
		Localizacao localizacao = null;
		ListaEncadeada<Localizacao> lista = new ListaEncadeada<Localizacao>();
		for (File arquivo : arquivos) {
			if(!arquivo.getName().equalsIgnoreCase("autoIncremento.txt")){
			try {
				file = new FileReader(arquivo);
				reader = new BufferedReader(file);
				localizacao = new Localizacao();
				localizacao.setId_localizacao(Integer.parseInt(reader.readLine()));
				localizacao.setId_prof(Integer.parseInt(reader.readLine()));
				localizacao.setIdStatus(Integer.parseInt(reader.readLine()));
				localizacao.setProf(daoProf.buscar(localizacao.getId_prof()));
				localizacao.setStatus(daoStatus.buscar(localizacao.getIdStatus()));
				lista.addFim(localizacao);
				reader.close();
				file.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			}
		}
		return lista;
	}

	@Override
	public Persistencia deletar(Localizacao e) {
		String path = Diretorios.LOCALIZACAO.getArquivo(e.getId_localizacao());
		File file = new File(path);
		if (file.delete())
			return Persistencia.REMOVIDO;
		return Persistencia.ERRO;
	}

	@Override
	public Localizacao buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persistencia atualizar(Localizacao e) {
		String path = Diretorios.LOCALIZACAO.getArquivo(e.getId_localizacao());
		BufferedWriter writer;
		FileWriter file;
		try {
			file = new FileWriter(path);
			writer = new BufferedWriter(file);
			writer.write(String.valueOf(e.getId_localizacao()));
			writer.newLine();
			writer.write(String.valueOf(e.getId_prof()));
			writer.newLine();
			writer.write(String.valueOf(e.getIdStatus()));
			writer.close();
			return Persistencia.GRAVADO;
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
		}
		return Persistencia.ERRO;
	}

	@Override
	public boolean validaNovoRegistro(Localizacao novo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Gera a lista de localizacoes e, posteriomente, chama o método para gerar o html
	 * @return Html contendo as localizações
	 */
	public String geraHtmlLocalizacao(){
		String html  = "";
		LocalizacaoDataAccess daoLocalizacao = new LocalizacaoDataAccess();
		AulaDataAccess daoAula = new AulaDataAccess();
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
