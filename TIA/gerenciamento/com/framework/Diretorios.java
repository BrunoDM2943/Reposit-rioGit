package com.framework;



public enum Diretorios {
	ROOT(System.getProperty("user.home") + "\\TIA"),
	DADOS(ROOT.getPath() + "\\Dados"),
	PROFESSORES(DADOS.getPath() +  "\\Professores"),
	CURSOS(DADOS.getPath() + "\\Cursos"),
	LOCALIZACAO(DADOS.getPath() +  "\\Localizacao"),
	INFORMACOES(ROOT.getPath()  + "\\Informações"),
	LOG(ROOT.getPath()  + "\\log"),
	AUTOINCREMENTO("\\autoIncremento.txt"),
	STATUS(DADOS.getPath() + "\\Status"),
	DOCENTE(DADOS.getPath() + "\\Docente"),
	SALA(DADOS.getPath() + "\\Sala"),
	AULA(DADOS.getPath() + "\\Aula"),
	DISCIPLINA(DADOS.getPath() + "\\Disciplinas");
	
	private String path;
	

	private Diretorios(String path) {
		this.path = path;
	}
	
	
	public String getPath() {
		return path;
	}
	
	public String getAutoIncremento() {
		return path + AUTOINCREMENTO.getPath();
	}
	
	public String getArquivo(int id) {
	    return path + "\\" + id +".txt";
	}
	
	@Override
	public String toString() {
	    return  path;
	}
	
		
}
