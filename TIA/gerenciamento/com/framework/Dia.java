package com.framework;

import java.util.Calendar;

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
	 * 
	 * @param dia dia da semana
	 */
	private Dia(String dia) {
		this.dia = dia;
	}

	/**
	 * Retorna uma representação escrita do objeto
	 * 
	 * @return dia da semana por extenso
	 */
	@Override
	public String toString() {
		return dia;
	}

	public static Dia getDia() {
		Calendar data = Calendar.getInstance();
		int dia_semana = data.get(Calendar.DAY_OF_WEEK);

		switch (dia_semana) {
		case 2:
			return SEG;
		case 3:
			return TER;
		case 4:
			return QUA;
		case 5:
			return QUI;
		case 6:
			return SEX;
		case 7:
			return SAB;
		default:
			return null;
		}
	}
}
