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
import com.tia.model.Sala;

/**
 * Classe responsável por persistir uma sala no banco
 * 
 * @author Bruno
 * @since 17/05/2014
 * 
 */
public class SalaDAO implements DAO<Sala> {

    @Override
    public Persistencia gravar(Sala e) {
	if (validaNovoRegistro(e)) {
	    try {
		BufferedWriter writer = new BufferedWriter(new FileWriter(
			Diretorios.SALA.getArquivo(e.getId_sala())));
		writer.write(String.valueOf(e.getId_sala()));
		writer.newLine();
		writer.write(e.getNome());
		writer.newLine();
		writer.write(String.valueOf(e.getAndar()));
		writer.newLine();
		writer.write(String.valueOf(e.hasEquipamento()));
		writer.close();
		return Persistencia.GRAVADO;
	    } catch (IOException e1) {
		System.err.println(e1.getMessage());
		e1.printStackTrace();
		return Persistencia.ERRO;
	    }
	} else {
	    SistemaArquivos.rollback(Diretorios.SALA.getAutoIncremento());
	    return Persistencia.DUPLICADO;
	}
    }

    @Override
    public ListaEncadeada<Sala> lerTodos() {
	File dir = new File(Diretorios.SALA.getPath());
	File[] arquivos = dir.listFiles();
	ListaEncadeada<Sala> lista = new ListaEncadeada<Sala>();
	BufferedReader reader;
	Sala sala;

	for (File arquivo : arquivos) {
	    // FIXME autoIncremento
	    if (!arquivo.getName().equals("autoIncremento.txt")) {
		try {
		    reader = new BufferedReader(new FileReader(arquivo));
		    sala = new Sala();
		    sala.setId_sala(Integer.parseInt(reader.readLine()));
		    sala.setNome(reader.readLine());
    		    sala.setAndar(Short.parseShort(reader.readLine()));
		    sala.setEquipamento(Boolean.parseBoolean(reader.readLine()));
		    lista.addFim(sala);
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

    /**
     * FIXME Quando uma sala for deletada, seu registro também deve ser removido
     * dos arquivos de aula
     */
    @Override
    public Persistencia deletar(Sala e) {
	File file = new File(Diretorios.SALA.getArquivo(e.getId_sala()));
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
    public Sala buscar(int id) {
    	BufferedReader reader;
    	Sala sala = null;
    	String arquivo = Diretorios.SALA.getArquivo(id);
    	try {
		    reader = new BufferedReader(new FileReader(arquivo));
		    sala = new Sala();
		    sala.setId_sala(Integer.parseInt(reader.readLine()));
		    sala.setNome(reader.readLine());
    		sala.setAndar(Short.parseShort(reader.readLine()));
		    sala.setEquipamento(Boolean.parseBoolean(reader.readLine()));		    
		    reader.close();
		    return sala;
		} catch (FileNotFoundException e) {
		    System.err.println(e.getMessage());
		    e.printStackTrace();
		} catch (IOException e) {
		    System.err.println(e.getMessage());
		    e.printStackTrace();
		}
	return null;
    }

    @Override
    public boolean validaNovoRegistro(Sala novo) {
	ListaEncadeada<Sala> lista = lerTodos();
	while (lista.hasNext()) {
	    if (lista.next().equals(novo))
		return false;
	}
	return true;
    }

    @Override
    public Persistencia atualizar(Sala e) {
	File file = new File(Diretorios.SALA.getArquivo(e.getId_sala()));
	try {
	    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
	    writer.write(String.valueOf(e.getId_sala()));
	    writer.newLine();
	    writer.write(e.getNome());
	    writer.newLine();
	    writer.write(String.valueOf(e.getAndar()));
	    writer.newLine();
	    writer.write(String.valueOf(e.hasEquipamento()));
	    writer.close();
	    return Persistencia.GRAVADO;
	} catch (IOException e1) {
	    System.err.println(e1.getMessage());
	    e1.printStackTrace();
	    return Persistencia.ERRO;
	}
    }

}
