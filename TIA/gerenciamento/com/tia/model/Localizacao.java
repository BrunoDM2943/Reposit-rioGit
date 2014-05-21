package com.tia.model;

import com.framework.Diretorios;
import com.framework.SistemaArquivos;

/**
 * @since 26/04/2014
 * @author Bruno
 * 
 */
public class Localizacao {
    private int idLocalizacao;
    private int idProf;
    private int idStatus;
    private Professor prof;
    private Status status;
    public Localizacao() {}

    

    public int getId_localizacao() {
        return idLocalizacao;
    }
    public int getId_prof() {
        return idProf;
    }

   
    
    public void setId_localizacao() {
	this.idLocalizacao = SistemaArquivos.geraChavePrimaria(Diretorios.LOCALIZACAO.getAutoIncremento());
    }

    public void setId_localizacao(int idLocalizacao) {
        this.idLocalizacao = idLocalizacao;
    }

    public void setId_prof(int id_prof) {
        this.idProf = id_prof;
    }

	public int getIdStatus() {
		return idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}



	public Professor getProf() {
		return prof;
	}



	public void setProf(Professor prof) {
		this.prof = prof;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}
}