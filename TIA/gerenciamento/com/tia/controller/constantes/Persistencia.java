package com.tia.controller.constantes;

/**
 * Constante responsável por mapear os tipos de gravação
 * @author bruno.martins
 * @since 15/05/2014
 */
public enum Persistencia {
    
    GRAVADO('G'), //A gravação foi feita com sucesso
    DUPLICADO('D'), //A gravação não foi feita, pois a entidade estava duplicada
    ERRO('E'), //Houve um erro na gravação!
    REMOVIDO('R');//Removido!
    
    
    char mensagem;

    /**
     * Construtor 
     * @param mensagem Tipo de gravação (G,D ou E)
     * @author bruno.martins
     * @since 15/05/2014
     */
    private Persistencia(char mensagem) {
	this.mensagem = mensagem;
    }
    
    
}
