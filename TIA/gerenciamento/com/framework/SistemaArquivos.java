package com.framework;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import alocacaoEstatica.fila.Fila;

import com.tia.model.Curso;
import com.tia.model.Professor;
import com.tia.model.Status;

public abstract class SistemaArquivos {

    /**
     * Método para retomar a chave primária de um registro
     * 
     * @param path
     *            Caminho do arquivo
     * @author Bruno
     * @since 03/05/2014
     */
    public static void rollback(String path) {
	StringBuilder builder = new StringBuilder();
	builder.append("A chave ");
	try {
	    System.out
		    .println("Voltando a chave primária para um valor anterior");
	    BufferedReader reader = new BufferedReader(new FileReader(path));
	    int chave = Integer.parseInt(reader.readLine());
	    builder.append(chave);
	    chave--;
	    reader.close();
	    BufferedWriter writer = new BufferedWriter(new FileWriter(path));
	    builder.append(" " + "voltou para " + chave);
	    writer.append(String.valueOf(chave));
	    reader.close();
	    writer.close();
	    System.out.println(builder.toString());
	} catch (IOException e) {
	    System.err.println(e.getMessage());
	    e.printStackTrace();
	}

    }

    /**
     * Gera uma chave primária para uma entidade
     * 
     * @param path
     *            Caminho da entidade
     * @return Chave primária
     * @author Bruno
     * @since 03/05/2014
     */
    public static int geraChavePrimaria(String path) {
	File arquivo = new File(path);
	int chave;
	try {
	    if (arquivo.exists()) {
		BufferedReader reader = new BufferedReader(new FileReader(
			arquivo));
		chave = Integer.parseInt(reader.readLine());
		reader.close();
		BufferedWriter writer = new BufferedWriter(new FileWriter(
			arquivo));
		chave++;
		writer.append(String.valueOf(chave));
		writer.close();
		return chave;
	    } else {
		BufferedWriter writer = new BufferedWriter(new FileWriter(
			arquivo));
		writer.write("0");
		writer.close();
		return 0;
	    }
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
	return 0;
    }

    /**
     * Valida a existência do diretório de arquivos do sistema
     * 
     * @author Bruno
     * @since 03/05/2014
     */
    public static void checarDiretorios() {

	Diretorios[] diretorios = Diretorios.values();

	for (int i = 0; i < diretorios.length; i++) {
	    System.out.println("Validando a existência do diretório: "
		    + diretorios[i].getPath());
	    File dir = new File(diretorios[i].getPath());
	    if (!dir.exists()) {
		System.out.println("Criando diretório: "
			+ diretorios[i].getPath());
		dir.mkdir();
	    } else {
		System.out.println("Diretório jó existente!");
	    }
	}
    }

    /**
     * Valida a existência de um novo registro
     * 
     * @param e
     *            Registro a ser validado
     * @param path
     *            Caminho do registro
     * @return True, se for novo; False, se já existir
     */
    public static boolean validaNovoRegistro(Object e, String path) {
	boolean novo = true;
	File[] dir = new File(path).listFiles();
	Fila fila = new Fila(dir);
	BufferedReader reader;
	Professor prof = null;
	Status status = null;
	Curso curso = null;

	if (e.getClass().equals(Professor.class)) {
	    prof = (Professor) e;
	} else if (e.getClass().equals(Status.class)) {
	    status = (Status) e;
	} else if (e.getClass().equals(Curso.class)) {
	    curso = (Curso) e;
	}

	while (fila.hasNext() && novo == true) {
	    try {
		reader = new BufferedReader(new FileReader(
			(File) fila.getInicio()));
		if (prof != null) {
		    novo = (!prof.getNome().equals(reader.readLine()));
		} else if (status != null) {
		    novo = (!status.getStatus().equals(reader.readLine()));
		} else if(curso !=null) {
		    novo = (!curso.getNome().equals(reader.readLine()));
		}

		fila.remove();
		reader.close();
		reader = null;
	    } catch (FileNotFoundException e1) {
		System.err.println(e1.getMessage());
		e1.printStackTrace();
	    } catch (IOException e1) {
		System.err.println(e1.getMessage());
		e1.printStackTrace();
	    }
	}
	return novo;
    }

    /**
     *
     * Retorna a chave primária de um registro!
     * 
     * @param nome
     * @param path
     * @return chavePrimaria
     */
    @SuppressWarnings("resource")
    public static int getChavePrimaria(String nome, String path) {
	File dir = new File(path);
	File[] arquivos = dir.listFiles();
	BufferedReader reader;
	for (int i = 0; i < arquivos.length; i++) {
	    try {
		reader = new BufferedReader(new FileReader(arquivos[i]));
		while(reader.ready()) {
		 String linha = reader.readLine();
		 if(linha.contains(nome)) {
		     String nomeArquivo = arquivos[i].getName();
		     nomeArquivo = nomeArquivo.substring(0, nomeArquivo.lastIndexOf('.'));
		     return Integer.parseInt(nomeArquivo); 
		 }	    		    
		}
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }

	}
	return -1;
    }
    
    
    /**
     * Transforma o nome de um arquivo para uma chave primária!
     * 
     * @param arquivo Nome do arquivo
     * @return chave primaria
     * @author bruno.martins
     * @since 07/05/2014
     */
    public static int toChavePrimaria(String arquivo) {
	return Integer.parseInt(arquivo.substring(0, arquivo.lastIndexOf('.')));
    }

}
