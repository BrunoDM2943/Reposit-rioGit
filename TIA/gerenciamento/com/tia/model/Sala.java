package com.tia.model;

import com.framework.Diretorios;
import com.framework.SistemaArquivos;

/**
 * @since 26/04/2014
 * @author Bruno
 * 
 */

/**
 * Entidade Sala
 * @author Bruno
 *
 */
public class Sala {
    private int id_sala;
    private short andar;
    private boolean equipamento;

    private String nome;

    public Sala() {}

    public int getAndar() {
	return andar;
    }

    public int getId_sala() {
	return id_sala;
    }

    public String getNome() {
	return nome;
    }

    public boolean hasEquipamento() {
	return equipamento;
    }

    public void setAndar(short andar) {
	this.andar = andar;
    }

    public void setEquipamento(boolean equipamento) {
	this.equipamento = equipamento;
    }

    public void setId_sala() {
	this.id_sala = SistemaArquivos.geraChavePrimaria(Diretorios.SALA
		.getAutoIncremento());
    }

    public void setId_sala(int id_sala) {
	this.id_sala = id_sala;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    @Override
    public String toString() {
	return getNome();
    }

    public boolean equals(Sala novo) {
	return novo.getNome().equals(this.toString());
    }
}
