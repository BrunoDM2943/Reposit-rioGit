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
import com.tia.model.Docente;

public class DocenteDataAccess implements DataAccessObject<Docente>{

	private ProfessorDataAccess daoProf = new ProfessorDataAccess();
	private CursoDataAccess daoCurso = new CursoDataAccess();
	
	@Override
	public Persistencia gravar(Docente e) {
		String path = Diretorios.DOCENTE.getArquivo(e.getProf().getId());
		BufferedWriter writer;
		if(validaNovoRegistro(e)){
			try {
				writer = new BufferedWriter(new FileWriter(path));
				writer.write(String.valueOf(e.getIdDocente()));
				writer.newLine();
				writer.write(String.valueOf(e.getProf().getId()));
				writer.newLine();
				while(e.getCursos().hasNext()){
					writer.write(String.valueOf(e.getCursos().next().getIdCurso()));
					writer.newLine();
				}
				writer.close();
				return Persistencia.GRAVADO;
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
				e1.printStackTrace();
				return Persistencia.ERRO;
			}
		}else{
			
		}
		
		return null;
	}

	@Override
	public ListaEncadeada<Docente> lerTodos() {
		String path = Diretorios.DOCENTE.getPath();
		File[] arquivos = new File(path).listFiles();
		BufferedReader reader;		
		Docente docente = null;	
		ListaEncadeada<Docente> lista = new ListaEncadeada<Docente>();
		
		for(File arquivo : arquivos){
			if(!arquivo.getName().equalsIgnoreCase("autoIncremento.txt")){
				try {
					reader = new BufferedReader(new FileReader(arquivo));
					docente = new Docente();
					docente.setIdDocente(Integer.parseInt(reader.readLine()));
					docente.setProf(daoProf.buscar(Integer.parseInt(reader.readLine())));
					while(reader.ready())						
						docente.getCursos().addFim(daoCurso.buscar(Integer.parseInt(reader.readLine())));					
					lista.addFim(docente);
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
	public Persistencia deletar(Docente e) {
		String path = Diretorios.DOCENTE.getArquivo(e.getIdDocente());
		File arquivo = new File(path);
		if(arquivo.delete())
			return Persistencia.REMOVIDO;
		return Persistencia.ERRO;		
	}

	@Override
	public Docente buscar(int id) {
		String path = Diretorios.DOCENTE.getArquivo(id);
		Docente docente = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			docente = new Docente();
			docente.setIdDocente(Integer.parseInt(reader.readLine()));
			docente.setProf(daoProf.buscar(Integer.parseInt(reader.readLine())));
			while(reader.ready())						
				docente.getCursos().addFim(daoCurso.buscar(Integer.parseInt(reader.readLine())));				
			reader.close();
			return docente;
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
	public Persistencia atualizar(Docente e) {
		String path = Diretorios.DOCENTE.getArquivo(e.getIdDocente());
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(path));
			writer.write(e.getProf().toString());
			writer.newLine();
			while(e.getCursos().hasNext()){
				writer.write(e.getCursos().next().getIdCurso());
				writer.newLine();
			}
			writer.close();
			return Persistencia.GRAVADO;
		} catch (FileNotFoundException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
		} catch (NumberFormatException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();
		}
		return Persistencia.ERRO;
	}

	@Override
	public boolean validaNovoRegistro(Docente novo) {
		ListaEncadeada<Docente> lista = lerTodos();
		while(lista.hasNext()){
			if(lista.next().equals(novo))
				return false;
		}
		return true;
	}
	

}
