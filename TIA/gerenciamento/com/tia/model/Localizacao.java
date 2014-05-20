package com.tia.model;

import com.framework.Diretorios;
import com.framework.SistemaArquivos;

/**
 * @since 26/04/2014
 * @author Bruno
 * 
 */
public class Localizacao {
    private int id_localizacao;
    private int id_prof;
    private int id_sala;
    private int id_status;
    private String nomeProfessor;
    private String nomeSala;

    private String status;

    public Localizacao() {
    }

    /**
     * FIXME Composição?
     * 
     * @param prof
     * @param status
     */
    public Localizacao(int id,Professor prof, Status status, Sala sala) {
	this.id_localizacao = id;
	this.nomeProfessor = prof.getNome();
	this.id_prof = prof.getId();
	this.status = status.getStatus();
	this.id_status = status.getId();
	this.id_sala = sala.getId_sala();
	this.nomeSala  = sala.getNome();
	
    }

    public int getId_localizacao() {
        return id_localizacao;
    }
    public int getId_prof() {
        return id_prof;
    }

    public int getId_sala() {
        return id_sala;
    }

    public int getId_status() {
        return id_status;
    }
    
    public String getNomeProfessor() {
	return nomeProfessor;
    }
    
    public String getNomeSala() {
        return nomeSala;
    }
    
    public String getStatus() {
	return status;
    }
    
    public void setId_localizacao() {
	this.id_localizacao = SistemaArquivos.geraChavePrimaria(Diretorios.LOCALIZACAO.getAutoIncremento());
    }

    public void setId_localizacao(int id_localizacao) {
        this.id_localizacao = id_localizacao;
    }

    public void setId_prof(int id_prof) {
        this.id_prof = id_prof;
    }

    public void setId_sala(int id_sala) {
        this.id_sala = id_sala;
    }

    public void setId_status(int id_status) {
        this.id_status = id_status;
    }

    public void setNomeProfessor(String nomeProfessor) {
	this.nomeProfessor = nomeProfessor;
    }

    public void setNomeSala(String nomeSala) {
        this.nomeSala = nomeSala;
    }

    public void setProfessor(Professor prof) {
	this.id_prof = prof.getId();
	this.nomeProfessor = prof.getNome();
    }

    public void setSala(Sala sala) {
	this.id_sala = sala.getId_sala();
	this.nomeSala  = sala.getNome();
    }
    
    

    public void setStatus(Status status) {
	this.id_status = status.getId();
	this.status = status.getStatus();
    }
    
    public void setStatus(String status) {
	this.status = status;
    }
    
    public String toString() {
	String mensagem = "Professor: " +nomeProfessor + "\n"
		+ "Status: " + status + "\n"
		+ "Sala: " + nomeSala;
	return mensagem;
    }
}
