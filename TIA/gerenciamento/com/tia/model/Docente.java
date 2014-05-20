package com.tia.model;

import com.framework.Diretorios;
import com.framework.SistemaArquivos;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

/**
 * Entidade responsável por docentes
 * 
 * @author bruno.martins
 * @since 19/05/2014
 * @version 19/05/2014
 */
public class Docente {
	private int idDocente;
	private Professor prof;
	private ListaEncadeada<Curso> cursos = new ListaEncadeada<Curso>();
	
	/**
	 * Construtor
	 */
	public Docente(){}
	
	/**
	 * Retorna um professor
	 * @return Professor
	 */
	public Professor getProf() {
		return prof;
	}
	
	/**
	 * Atribui um professor para o docente
	 * @param prof Professor
	 */
	public void setProf(Professor prof) {
		this.prof = prof;
	}
	
	/**
	 * Retorna uma lista encadeada contendo os cursos relacionados ao professor
	 * @return lista de cursos
	 */
	public ListaEncadeada<Curso> getCursos() {
		return cursos;
	}
	
	/**
	 * Atribui uma lista encadeada de cursos relacionados ao professor
	 * @param cursos cursos relacionados
	 */
	public void setCursos(ListaEncadeada<Curso> cursos) {
		this.cursos = cursos;
	}
	
	/**
	 * @author bruno.martins
	 * @since 19/05/2014
	 * @version 19/05/2014
	 * @return Uma representação do objeto em String
	 */
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(this.prof);
		builder.append("-");
		while(cursos.hasNext())
			builder.append(cursos.next().getCodigo() + "/");
		return builder.toString();
	}
	
	/**
	 * Verifica se um docente já existe no registro
	 * @param e Docente que vai ser verificado
	 * @return true, se for igual, false senão
	 */
	public boolean equals(Docente e){
		return (this.prof.equals(e.getProf()));
	}

	/**
	 * Retorna o id do docente
	 * @return id do docente
	 */
	public int getIdDocente() {
		return idDocente;
	}
	
	/**
	 * Atribui um id para o docene
	 * @param idDocente id
	 */
	public void setIdDocente(int idDocente) {
		this.idDocente = idDocente;
	}
	
	/**
	 * Gera uma chave primária para o docente
	 */
	public void setIdDocente(){
		this.idDocente = SistemaArquivos.geraChavePrimaria(Diretorios.DOCENTE.getAutoIncremento());
	}
}
