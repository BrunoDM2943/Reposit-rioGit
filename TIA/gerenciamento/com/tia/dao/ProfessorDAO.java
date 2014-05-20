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
import com.framework.SistemaArquivos;
import com.tia.controller.constantes.Persistencia;
import com.tia.model.Professor;

public class ProfessorDAO implements DAO<Professor> {

	@Override
	public Persistencia gravar(Professor e) {
		if (validaNovoRegistro(e)) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						Diretorios.PROFESSORES.getArquivo(e.getId())));
				writer.write(String.valueOf(e.getId()));
				writer.newLine();
				writer.write(e.getNome());
				writer.close();
				return Persistencia.GRAVADO;
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
				e1.printStackTrace();
				SistemaArquivos.rollback(Diretorios.PROFESSORES
						.getAutoIncremento());
				return Persistencia.ERRO;
			}

		} else {
			SistemaArquivos
					.rollback(Diretorios.PROFESSORES.getAutoIncremento());
			return Persistencia.DUPLICADO;
		}
	}

	@Override
	public ListaEncadeada<Professor> lerTodos() {
		File[] files = new File(Diretorios.PROFESSORES.getPath()).listFiles();
		ListaEncadeada<Professor> lista = new ListaEncadeada<Professor>();
		BufferedReader reader;
		Professor prof;
		for (File file : files) {
			if (!file.getName().equals("autoIncremento.txt")) {
				try {
					reader = new BufferedReader(new FileReader(file));
					prof = new Professor();
					prof.setId(Integer.parseInt(reader.readLine()));
					prof.setNome(reader.readLine());
					lista.addFim(prof);
					reader.close();
				} catch (FileNotFoundException e) {
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
	public Persistencia atualizar(Professor e) {
		File file = new File(Diretorios.SALA.getArquivo(e.getId()));
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(String.valueOf(e.getId()));
			writer.newLine();
			writer.write(e.getNome());
			writer.close();
			return Persistencia.GRAVADO;
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
			return Persistencia.ERRO;
		}
	}

	/**
	 * FIXME Quando um professor for deletado, seu registro deve também ser
	 * removido da lista de docente
	 */
	@Override
	public Persistencia deletar(Professor e) {
		File file = new File(Diretorios.PROFESSORES.getArquivo(e.getId()));
		try {
			file.delete();
			return Persistencia.REMOVIDO;
		} catch (SecurityException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
			return Persistencia.ERRO;
		}
	}

	
	@Override
	public Professor buscar(int id) {
		String path = Diretorios.PROFESSORES.getArquivo(id);
		Professor prof = null;
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(path));
			prof = new Professor();
			prof = new Professor();
			prof.setId(Integer.parseInt(reader.readLine()));
			prof.setNome(reader.readLine());
			reader.close();
			return prof;
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
	public boolean validaNovoRegistro(Professor novo) {
		ListaEncadeada<Professor> lista = lerTodos();
		while (lista.hasNext()) {
			if (lista.next().equals(novo))
				return false;
		}
		return true;

	}

}
