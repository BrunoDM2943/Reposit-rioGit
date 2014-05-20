package com.tia.model;

import com.framework.Diretorios;
import com.framework.SistemaArquivos;

/**
 * 
 * @since 26/04/2014
 * @author Bruno
 * 
 */
public class Professor {
   
    private int id_professor;
    private String nome;

    public Professor() {}

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public int getId() {
	return id_professor;
    }

    public void setId() {
	this.id_professor = SistemaArquivos
		.geraChavePrimaria(Diretorios.PROFESSORES.getAutoIncremento());
    }

    public void setId(int id) {
	this.id_professor = id;
    }

    @Override
    public String toString() {
	return getNome();
    }

    public boolean equals(Professor prof) {
	return this.nome.equalsIgnoreCase(prof.getNome());
    }

}
