package com.tia.controller.cadastro;

import java.util.Hashtable;

import com.tia.controller.constantes.Persistencia;

/**
 * Serviço de validações
 * @author bruno.martins
 * @since 15/05/2014
 *
 */
public interface ValidarCadastro {
	
	/**
	 * Valida as entradas para um novo cadastro
	 * @param parametros Tabela de espalhamento contendo os campos da entrada
	 * @return True, se todos os campos foram aprovado; False, se não
	 * @author bruno.martins
	 */
    public boolean validaEntradas(Hashtable<String, Object> parametros);

    /**
     * Chama a classe responsável pelo DAO da entidade
     * @param parametros Tabela de espalhamento contendo os atributos
     * @return GRAVADO, se a gravação for um sucesso; ERRO, se houver qualquer tipo de erro; DUPLICADO, se o arquivo já existir
     * @author bruno.martins
     * 
     */
    public Persistencia persistir(Hashtable<String, Object> parametros);

    /**
     * Valida a persistência de uma entidade
     * @param response Resposta da persistência
     * @author bruno.martins
     */
    public void validaPersistencia(Persistencia response);

}
