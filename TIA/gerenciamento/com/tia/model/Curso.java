package com.tia.model;

import java.util.Hashtable;

import com.framework.Diretorios;
import com.framework.SistemaArquivos;

/**
 * 
 * @since 26/04/2014
 * @author Bruno
 * 
 */

public class Curso {

    
    private int idCurso;
    private String nome;
    private String codigo;
    private int qtdSemestres;
    private Hashtable<String, Boolean> turnos = new Hashtable<String, Boolean>();

    public Curso() {
    }
    public boolean equal(Curso curso) {
	if(this.nome.equals(curso.getNome()))
		return true;
	return false;
    }

    public String getCodigo() {
	return codigo;
    }

    public int getIdCurso() {
	return idCurso;
    }

    public String getNome() {
	return nome;
    }

    public int getQtdSemestres() {
        return qtdSemestres;
    }

    public void setCodigo(String codigo) {
	this.codigo = codigo;
    }

    public void setIdCurso() {
	this.idCurso = SistemaArquivos.geraChavePrimaria(Diretorios.CURSOS
		.getAutoIncremento());
    }

    public void setIdCurso(int idCurso) {
	this.idCurso = idCurso;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public void setQtdSemestres(int qtdSemestres) {
        this.qtdSemestres = qtdSemestres;
    }

    
    @Override
    public String toString() {
	return getNome();
    }
	public Hashtable<String, Boolean> getTurnos() {
		return turnos;
	}
	public void setTurnos(String k, boolean v) {
		this.turnos.put(k, v);
	}
}
