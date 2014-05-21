package com.tia.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.framework.Diretorios;
import com.tia.controller.constantes.Persistencia;
import com.tia.model.Status;

public class StatusDataAccess implements DataAccessObject<Status> {
	
	@Override
	public Persistencia gravar(Status e) {
		String path = Diretorios.STATUS.getArquivo(e.getId());
		BufferedWriter writer;
		if (validaNovoRegistro(e)) {
			try {
				writer = new BufferedWriter(new FileWriter(path));
				writer.write(String.valueOf(e.getId()));
				writer.newLine();
				writer.write(e.getStatus());
				writer.close();
				return Persistencia.GRAVADO;
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
				e1.printStackTrace();
				return Persistencia.ERRO;
			}
		} else {
			return Persistencia.DUPLICADO;
		}
	}

	@Override
	public ListaEncadeada<Status> lerTodos() {
		String path = Diretorios.STATUS.toString();
		File[] arquivos = new File(path).listFiles();
		BufferedReader reader;
		ListaEncadeada<Status> lista = new ListaEncadeada<Status>();
		Status status = null;
		for (File arquivo : arquivos) {
			if(!arquivo.getName().equalsIgnoreCase("autoIncremento.txt")){
			try {
				reader = new BufferedReader(new FileReader(arquivo));
				status = new Status();
				status.setId(Integer.parseInt(reader.readLine()));
				status.setStatus(reader.readLine());
				lista.addFim(status);
				reader.close();
			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
			}
		}

		return lista;
	}

	@Override
	public Persistencia deletar(Status e) {
		File file = new File(Diretorios.STATUS.getArquivo(e.getId()));
		if (file.delete())
			return Persistencia.REMOVIDO;
		return Persistencia.ERRO;
	}

	@Override
	public Status buscar(int id) {
		String path = Diretorios.STATUS.getArquivo(id);
		Status status = null;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			status = new Status();
			status.setId(Integer.parseInt(reader.readLine()));
			status.setStatus(reader.readLine());
			reader.close();
			return status;
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Persistencia atualizar(Status e) {
		String path = Diretorios.STATUS.getArquivo(e.getId());
		BufferedWriter writer;

		try {
			writer = new BufferedWriter(new FileWriter(path));
			writer.write(String.valueOf(e.getId()));
			writer.newLine();
			writer.write(e.getStatus());
			writer.close();
			return Persistencia.GRAVADO;
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
			return Persistencia.ERRO;
		}

	}

	@Override
	public boolean validaNovoRegistro(Status novo) {
		ListaEncadeada<Status> lista = lerTodos();
		while (lista.hasNext()) {
			if (lista.next().equals(novo))
				return false;
		}
		return true;
	}

}
