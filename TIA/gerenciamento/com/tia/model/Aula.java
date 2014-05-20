package com.tia.model;

import java.sql.Time;

import com.framework.Dia;
import com.framework.Diretorios;
import com.framework.SistemaArquivos;
import com.tia.controller.constantes.Turno;

/**
 * Classe responsável por conhecer os atributos de uma aula
 * @author Bruno
 * @since 19/05/2014
 * @version 19/05/2014
 */
public class Aula {
	
	private int idAula;
	private Disciplina disc;
	private Professor prof;
	private Turno turno;	
	private Sala sala;
	private Dia dia;
	private Time ini;
	private Time fim;
	
	public Aula(){}
	
	/**
	 * Retorna o id da aula
	 * @return IdAula
	 */
	public int getIdAula() {
		return idAula;
	}
	/**
	 * Atribui um valor para o id da aula
	 * @param idAula
	 */
	public void setIdAula(int idAula) {
		this.idAula = idAula;
	}
	/**
	 * Atribui uma chave primária ao id da aula
	 */
	public void setIdAula(){
		this.idAula = SistemaArquivos.geraChavePrimaria(Diretorios.AULA.getAutoIncremento());
	}
	/**
	 * Retorna a disciplia
	 * @return Disciplina
	 */
	public Disciplina getDisc() {
		return disc;
	}
	/**
	 * Atribui uam disciplina para esta aula
	 * @param disc
	 */
	public void setDisc(Disciplina disc) {
		this.disc = disc;
	}
	/**
	 * Retorna o professor
	 * @return Professor
	 */
	public Professor getProf() {
		return prof;
	}
	/**
	 * Atribui um professor para essa aula
	 * @param prof
	 */
	public void setProf(Professor prof) {
		this.prof = prof;
	}
	/**
	 * Retorna o Turno
	 * @return Turno
	 */
	public Turno getTurno() {
		return turno;
	}
	/**
	 * Atribui um turno para essa Aula
	 * @param turno
	 */
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	/**
	 * Retorna a sala
	 * @return Sala
	 */
	public Sala getSala() {
		return sala;
	}
	/**
	 * Atribui uma sala para essa Aula
	 * @param sala
	 */
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	/**
	 * Retorna o dia da semana da aula
	 * @return Dia
	 */ 
	public Dia getDia() {
		return dia;
	}
	/**
	 * Atribui um dia da semana para essa aula
	 * @param dia
	 */
	public void setDia(Dia dia) {
		this.dia = dia;
	}
	/**
	 * Retorna o horario de Inicio dessa aula
	 * @return Horário de Inínico
	 */
	public Time getIni() {
		return ini;
	}
	/**
	 * Atribui um horário de Início para aula
	 * @param ini
	 */
	public void setIni(Time ini) {
		this.ini = ini;
	}
	/**
	 * Retorna um horário de fim para essa aula
	 * @return Horário de fim
	 */
	public Time getFim() {
		return fim;
	}
	/**
	 * Atribui um ho´rario de fim para essa aula
	 * @param fim
	 */
	public void setFim(Time fim) {
		this.fim = fim;
	}
	
	/**
	 * Retorna uma representação em texto do objeto dessa classe
	 */
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(disc.getNome() + "\n");
		builder.append(prof.getNome() + "\n");
		builder.append(disc.getNomeCurso() + "-" + turno.toString() + "\n");
		builder.append(sala.getNome());
		return builder.toString();
	}
	
	/**
	 * Verifca se uma aula é igual a uma nova
	 * @param aula Aula nova
	 * @return True, se iguais, False se diferentes
	 */
	public boolean equals(Aula aula){
		if(this.disc.getNome().equals(aula.getDisc().getNome()) &&
				this.turno.equals(aula.getTurno()))
			return true;
		return false;
	}
}
