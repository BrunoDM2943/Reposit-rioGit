package com.tia.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.framework.Diretorios;
import com.tia.controller.constantes.Persistencia;
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

}
