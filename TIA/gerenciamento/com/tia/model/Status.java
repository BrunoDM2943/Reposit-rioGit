package com.tia.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.framework.Diretorios;
import com.framework.SistemaArquivos;


public class Status {
	
	private int id;
	private String status;
	
	public Status() {}
	
	/**
	 * Esse construtor recebe um id, e procura o registro do Status pelo id
	 * @param id Id do Status
	 * @author bruno.martins
	 * @since 07/05/2014
	 */
	public Status(int id) {
	    try {
		BufferedReader reader = new BufferedReader(new FileReader(Diretorios.STATUS.getArquivo(id)));
		this.status = reader.readLine();
		reader.close();
	    } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id_status) {
		this.id = id_status;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setId() {
		this.id = SistemaArquivos.geraChavePrimaria(Diretorios.STATUS.getAutoIncremento());
	}
	
	public String toString() {
		return getStatus();
	}
	
}
