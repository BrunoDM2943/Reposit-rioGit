package com.tia.model;

import com.framework.Diretorios;
import com.framework.SistemaArquivos;

public class Disciplina {
	
	private int idDisciplina;
	private int idCurso;
	private int semestre;
	private String nome;
	private String nomeCurso;
	
	public Disciplina(){}
	
	public boolean equals(Disciplina disc){
		if(this.nome.equalsIgnoreCase(disc.getNome()) && 
				this.idCurso == disc.getIdCurso())
			return true;
		return false;
	}
	public int getIdCurso() {
		return idCurso;
	}
	public int getIdDisciplina() {
		return idDisciplina;
	}
	public String getNome() {
		return nome;
	}
	public String getNomeCurso() {
		return nomeCurso;
	}
	public int getSemestre() {
		return semestre;
	}
	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}
	public void setIdDisciplina() {
		this.idDisciplina = SistemaArquivos.geraChavePrimaria(Diretorios.DISCIPLINA.getAutoIncremento());
	}
	public void setIdDisciplina(int idDisciplina) {
		this.idDisciplina = idDisciplina;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}
	
	public void setSemestre(int semestre) {
		this.semestre = semestre;
	}
	
	@Override
	public String toString(){
		return this.nome;
	}
}
