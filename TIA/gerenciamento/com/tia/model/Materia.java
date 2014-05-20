package com.tia.model;

import com.framework.Diretorios;
import com.framework.SistemaArquivos;

/**@FIXME
 * 
 * Tratar em reevolução
 * @since 26/04/2014
 * @author Bruno
 */
public class Materia {
	private int id;
	private int id_professor;
	private int id_curso;
	

	public Materia() {}
	
	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}
	
	public void setId() {
	    this.id = SistemaArquivos.geraChavePrimaria(Diretorios.DOCENTE.getAutoIncremento());
	}

	public int getId_professor() {
	    return id_professor;
	}

	public void setId_professor(int id_professor) {
	    this.id_professor = id_professor;
	}

	public int getId_curso() {
	    return id_curso;
	}

	public void setId_curso(int id_curso) {
	    this.id_curso = id_curso;
	}
}
