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
import com.tia.model.Curso;

public class CursoDAO implements DAO<Curso> {

	@Override
	public Persistencia gravar(Curso e) {
		String path = Diretorios.CURSOS.getArquivo(e.getIdCurso());

		if (validaNovoRegistro(e)) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(path));
				writer.write(String.valueOf(e.getIdCurso()));
				writer.newLine();
				writer.write(e.getNome());
				writer.newLine();
				writer.write(e.getCodigo());
				writer.newLine();
				writer.write(String.valueOf(e.getTurnos().get("matutino")));
				writer.newLine();
				writer.write(String.valueOf(e.getTurnos().get("vespertino")));
				writer.newLine();
				writer.write(String.valueOf(e.getTurnos().get("noturno")));
				writer.newLine();
				writer.write(String.valueOf(e.getQtdSemestres()));
				writer.close();
				return Persistencia.GRAVADO;
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
				e1.printStackTrace();
				SistemaArquivos.rollback(Diretorios.CURSOS.getAutoIncremento());
				return Persistencia.ERRO;
			}
		} else {
			SistemaArquivos.rollback(Diretorios.CURSOS.getAutoIncremento());
			return Persistencia.DUPLICADO;
		}
	}

	@Override
	public ListaEncadeada<Curso> lerTodos() {
		File[] arquivos = new File(Diretorios.CURSOS.getPath()).listFiles();
		ListaEncadeada<Curso> lista = new ListaEncadeada<Curso>();
		BufferedReader reader;
		Curso curso;
		for (File arquivo : arquivos) {
			if (!arquivo.getName().contentEquals("autoIncremento.txt")) {
				try {
					reader = new BufferedReader(new FileReader(arquivo));
					curso = new Curso();
					curso.setIdCurso(Integer.parseInt(reader.readLine()));
					curso.setNome(reader.readLine());
					curso.setCodigo(reader.readLine());
					curso.setTurnos("matutino",
							Boolean.valueOf(reader.readLine()));
					curso.setTurnos("vespertino",
							Boolean.valueOf(reader.readLine()));
					curso.setTurnos("noturno",
							Boolean.valueOf(reader.readLine()));
					curso.setQtdSemestres(Integer.parseInt(reader.readLine()));
					reader.close();
					lista.addFim(curso);
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
	public Curso buscar(int id) {
		String path = Diretorios.CURSOS.getArquivo(id);
		Curso curso = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			curso = new Curso();
			curso.setIdCurso(Integer.parseInt(reader.readLine()));
			curso.setNome(reader.readLine());
			curso.setCodigo(reader.readLine());
			curso.setTurnos("matutino", Boolean.valueOf(reader.readLine()));
			curso.setTurnos("vespertino", Boolean.valueOf(reader.readLine()));
			curso.setTurnos("noturno", Boolean.valueOf(reader.readLine()));
			curso.setQtdSemestres(Integer.parseInt(reader.readLine()));
			reader.close();
			return curso;
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
	public Persistencia deletar(Curso e) {
		File file = new File(Diretorios.CURSOS.getArquivo(e.getIdCurso()));
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
	public Persistencia atualizar(Curso e) {
		File file = new File(Diretorios.CURSOS.getArquivo(e.getIdCurso()));
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(String.valueOf(e.getIdCurso()));
			writer.newLine();
			writer.write(e.getNome());
			writer.newLine();
			writer.write(e.getCodigo());
			writer.newLine();
			writer.write(String.valueOf(e.getTurnos().get("matutino")));
			writer.newLine();
			writer.write(String.valueOf(e.getTurnos().get("vespertino")));
			writer.newLine();
			writer.write(String.valueOf(e.getTurnos().get("noturno")));
			writer.newLine();
			writer.write(String.valueOf(e.getQtdSemestres()));
			writer.close();
			return Persistencia.GRAVADO;
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
			return Persistencia.ERRO;
		}
	}

	@Override
	public boolean validaNovoRegistro(Curso novo) {
		ListaEncadeada<Curso> lista = lerTodos();
		while (lista.hasNext()) {
			if (lista.next().equal(novo))
				return false;
		}
		return true;
	}
}
