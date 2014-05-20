package com.tia.controller.constantes;

/**
 * Constante de turnos
 * @author bruno.martins
 * @since 15/05/2014
 */
public enum Turno {
    M("Matutino"),
    V("Verspertino"),
    N("Noturno");
    
    private String turno;
    
    private Turno(String turno) {
	this.turno = turno;
    }
    
    public void setTurno(String turno){
    	this.turno = turno;
    }
    
    @Override
    public String toString() {
    	return turno;
    }

    public boolean equals(Turno turno){
    	return this.turno.equalsIgnoreCase(turno.toString());
    }
}
