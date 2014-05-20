package com.framework;

/**
 * Enum responsável por manter os dias da semanas
 * 
 * @author Bruno
 * @since 19/05/2014
 * @version 19/05/2014
 */
public enum Dia {
	SEG("Segunda"),
	TER("Terca"),
	QUA("Quarta"),
	QUI("Quinta"),
	SEX("Sexta"),
	SAB("Sábado");
	
	private String dia;
	
	/**
	 * Construtor
	 * @param dia dia da semana
	 */
	private Dia(String dia){
		this.dia = dia;
	}

	/**
	 * Retorna uma representação escrita do objeto
	 * @return dia da semana por extenso 
	 */
	@Override
	public String toString(){
		return dia;
	}
}
