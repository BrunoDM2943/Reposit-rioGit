package com.framework;


/**
 * Constante que mapeia os diretorios que serão usados pelo sistema 
 * @author bruno.martins
 * @since 02/05/2014
 */
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
	DISCIPLINA(DADOS.getPath() + "\\Disciplinas"),
	NOTICIAS(DADOS.getPath() + "\\Noticias"),
	LAYOUT(DADOS.getPath() + "\\Layout");
	
	private String path;
	
	/**
	 * Construtor
	 * @param path caminho
	 */
	private Diretorios(String path) {
		this.path = path;
	}
	
	/**
	 * Retorna o caminho do diretório
	 * @return Caminho
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Retorna o caminho do arquivo de auto incremento da entidade
	 * @return Caminho do arquivo
	 */
	public String getAutoIncremento() {
		return path + AUTOINCREMENTO.getPath();
	}
	/**
	 * Retorna o caminho de um registro
	 * @param id ID do registro
	 * @return caminho do registro
	 */
	public String getArquivo(int id) {
	    return path + "\\" + id +".txt";
	}
	
	@Override
	public String toString() {
	    return  path;
	}
	
		
}
