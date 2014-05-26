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
import com.framework.HtmlGenerator;
import com.tia.controller.constantes.Persistencia;
import com.tia.model.Noticia;

/**
 * Classe responsável por controlar o DAO da entidade Noticia
 * 
 * @author bruno.martins
 * @since 21/05/2014
 * @version 21/05/2014
 */
public class NoticiaDataAccess implements DataAccessObject<Noticia> {

	@Override
	public Persistencia gravar(Noticia e) {
		String path = Diretorios.NOTICIAS.getArquivo(e.getIdNoticia());
		FileWriter fileWriter;
		BufferedWriter writer;
		if (validaNovoRegistro(e)) {
			try {
				fileWriter = new FileWriter(path);
				writer = new BufferedWriter(fileWriter);
				writer.write(String.valueOf(e.getIdNoticia()));
				writer.newLine();
				writer.write((e.getTitulo()));
				writer.newLine();
				writer.write(e.getTexto());
				writer.close();
				return Persistencia.GRAVADO;
			} catch (IOException e1) {
				System.err.println(e1.getMessage());
				e1.printStackTrace();
				return Persistencia.ERRO;
			}
		}
		return Persistencia.DUPLICADO;
	}

	@Override
	public ListaEncadeada<Noticia> lerTodos() {
		String path = Diretorios.NOTICIAS.toString();
		File[] arquivos = new File(path).listFiles();
		FileReader fileReader;
		BufferedReader reader;
		Noticia noticia;
		ListaEncadeada<Noticia> lista = new ListaEncadeada<Noticia>();
		for (File arquivo : arquivos) {
			if (!arquivo.getName().equalsIgnoreCase("autoIncremento.txt")) {
				try {
					fileReader = new FileReader(arquivo);
					reader = new BufferedReader(fileReader);
					noticia = new Noticia();
					noticia.setIdNoticia(Integer.parseInt(reader.readLine()));
					noticia.setTitulo(reader.readLine());
					noticia.setTexto(reader.readLine());
					lista.addFim(noticia);
					reader.close();
					fileReader.close();
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
	public Persistencia deletar(Noticia e) {
		String path = Diretorios.NOTICIAS.getArquivo(e.getIdNoticia());
		File arquivo = new File(path);
		if (arquivo.delete())
			return Persistencia.REMOVIDO;
		return Persistencia.ERRO;
	}

	@Override
	public Noticia buscar(int id) {
		String path = Diretorios.NOTICIAS.getArquivo(id);
		FileReader fileReader;
		BufferedReader reader;
		Noticia noticia;
		try {
			fileReader = new FileReader(path);
			reader = new BufferedReader(fileReader);
			noticia = new Noticia();
			noticia.setIdNoticia(Integer.parseInt(reader.readLine()));
			noticia.setTitulo(reader.readLine());
			noticia.setTexto(reader.readLine());
			reader.close();
			fileReader.close();
			return noticia;
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
	public Persistencia atualizar(Noticia e) {
		String path = Diretorios.NOTICIAS.getArquivo(e.getIdNoticia());
		FileWriter fileWriter;
		BufferedWriter writer;
		try {
			fileWriter = new FileWriter(path);
			writer = new BufferedWriter(fileWriter);
			writer.write(String.valueOf(e.getIdNoticia()));
			writer.newLine();
			writer.write((e.getTitulo()));
			writer.newLine();
			writer.write(e.getTexto());
			writer.close();
		} catch (IOException e1) {
			System.err.println(e1.getMessage());
			e1.printStackTrace();			
		}
		return Persistencia.ERRO;
	}

	@Override
	public boolean validaNovoRegistro(Noticia novo) {
		ListaEncadeada<Noticia> lista = lerTodos();
		while(lista.hasNext()){
			if(novo.equals(lista.next()))
				return false;
		}
		return true;
	}
	
	public String geraNoticia(){
		String path = Diretorios.NOTICIAS.toString();
		File[] arquivos = new File(path).listFiles();
		FileReader fileReader;
		BufferedReader reader;
		Noticia noticia;
		ListaEncadeada<Object> lista = new ListaEncadeada<Object>();
		String txt = "";
		try{
		for (File arquivo : arquivos) {
		
			if (!arquivo.getName().equalsIgnoreCase("autoIncremento.txt")) {
				try {
					fileReader = new FileReader(arquivo);
					reader = new BufferedReader(fileReader);
					noticia = new Noticia();
					noticia.setIdNoticia(Integer.parseInt(reader.readLine()));
					noticia.setTitulo(reader.readLine());
					noticia.setTexto(reader.readLine());
					lista.addFim(noticia);
					reader.close();
					fileReader.close();
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
		HtmlGenerator generator =  new HtmlGenerator();
		txt = generator.gerarHtml(lista);
		}catch(NullPointerException e){
			return null;
		}
		return txt;
	}
	
	
}
