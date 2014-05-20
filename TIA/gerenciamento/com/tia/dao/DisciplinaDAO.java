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
import com.tia.model.Disciplina;

public class DisciplinaDAO implements DAO<Disciplina> {

	@Override
	public Persistencia gravar(Disciplina e) {
		String path = Diretorios.DISCIPLINA.getArquivo(e.getIdDisciplina());
		BufferedWriter writer;
		if(validaNovoRegistro(e)){
			try{
				writer = new BufferedWriter(new FileWriter(path));
				writer.write(String.valueOf(e.getIdDisciplina()));
				writer.newLine();
				writer.write(e.getNome());
				writer.newLine();
				writer.write(String.valueOf(e.getIdCurso()));
				writer.newLine();
				writer.write(e.getNomeCurso());
				writer.newLine();
				writer.write(String.valueOf(e.getSemestre()));
				writer.close();		
				return Persistencia.GRAVADO;
			}catch(FileNotFoundException e1){
				System.err.println(e1.getMessage());
				e1.printStackTrace();
				return Persistencia.ERRO;
			}catch(IOException e1){
				System.err.println(e1.getMessage());
				e1.printStackTrace();
				return Persistencia.ERRO;
			}
		}
		return Persistencia.DUPLICADO;
	}

	@Override
	public ListaEncadeada<Disciplina> lerTodos() {
		File[] arquivos = new File(Diretorios.DISCIPLINA.toString()).listFiles();
		ListaEncadeada<Disciplina> lista = new ListaEncadeada<Disciplina>();
		BufferedReader reader;
		Disciplina disc;		
		for(File arquivo : arquivos){
			if(!arquivo.getName().equalsIgnoreCase("autoIncremento.txt")){
				try{
					reader = new BufferedReader(new FileReader(arquivo));					
					disc = new Disciplina();
					disc.setIdDisciplina(Integer.parseInt(reader.readLine()));
					disc.setNome(reader.readLine());
					disc.setIdCurso(Integer.parseInt(reader.readLine()));
					disc.setNomeCurso(reader.readLine());
					disc.setSemestre(Integer.parseInt(reader.readLine()));
					lista.addFim(disc);
					reader.close();
				}catch(FileNotFoundException e){
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
	public Persistencia deletar(Disciplina e) {
		File arquivo = new File(Diretorios.DISCIPLINA.getArquivo(e.getIdDisciplina()));
		if(arquivo.delete())
			return Persistencia.REMOVIDO;
		return Persistencia.ERRO;
	}

	@Override
	public Disciplina buscar(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Persistencia atualizar(Disciplina e) {
		File file = new File(Diretorios.DISCIPLINA.getArquivo(e.getIdDisciplina()));
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(String.valueOf(e.getIdDisciplina()));
			writer.newLine();
			writer.write(e.getNome());
			writer.newLine();
			writer.write(String.valueOf(e.getIdCurso()));
			writer.newLine();
			writer.write(e.getNomeCurso());
			writer.newLine();
			writer.write(String.valueOf(e.getSemestre()));
			writer.close();		
		    return Persistencia.GRAVADO;
		} catch (IOException e1) {
		    System.err.println(e1.getMessage());
		    e1.printStackTrace();
		    return Persistencia.ERRO;
		}
	}

	@Override
	public boolean validaNovoRegistro(Disciplina novo) {
		ListaEncadeada<Disciplina> lista = lerTodos();
		while(lista.hasNext()){
			if(lista.next().equals(novo))
				return false;
		}
		return true;
	}

}
