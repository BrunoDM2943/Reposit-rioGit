package com.framework;


public enum Mensagens {
	SUCESSO("Arquivo gravado com sucesso!"),
	FALHA("Falha na gravação do arquivo!"),
	SEMSTATUS("Não há status cadastrado!"),
	SEMPROFESSOR("Não há professor cadastrado!"),
	ERRO("Erro"),
	CAMPOVAZIO("Os seguintes campos não podem ser vazios:");
	
	private String mensagem;
	
	
	private Mensagens(String mensagem) {
		this.mensagem = mensagem;
	}
	
	@Override
	public String toString() {
		return mensagem;
	}
}
