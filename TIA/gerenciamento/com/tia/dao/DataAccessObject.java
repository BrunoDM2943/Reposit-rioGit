package com.tia.dao;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.controller.constantes.Persistencia;

/**
 * Interface responsável por declarar os serviços a serem feitos no banco
 * @author Bruno
 * @since 16/05/2014
 */
public interface DataAccessObject<T> {
    
	/**
	 * Grava um objeto no banco de dados
	 * @param e Objeto a ser gravado
	 * @return GRAVADO, se a gravação for um sucesso; ERRO, se houver qualquer tipo de erro; DUPLICADO, se o arquivo já existir
	 * @author bruno.martins
	 * @since 16/05/2014
	 */
    public Persistencia gravar(T e);
    
    /**
     * Lê todos os objetos cadastrados no banco de dados
     * @return Lista Encadeada contendo os objetos
     * @author bruno.martins
     * @since 16/05/2014
     */
    public ListaEncadeada<T> lerTodos();
    
    /**
     * Deleta um registro do banco de dados
     * @param e Objeto a ser deletado
     * @return REMOVIDO, se o objeto foi removido com sucesso; ERRO, se ocorrer qualquer erro na deleção
     * @author bruno.martins
     * @since 16/05/2014
     */
    public Persistencia deletar(T e);
    
    /**
     * Busca um objeto através do seu ID no banco
     * @param id ID do objeto
     * @return objeto
	 * @author bruno.martins
     * @since 16/05/2014
     * @throws NullPointerExcpetion 
     */
    public T buscar(int id);

    /**
     * Atualiza um objeto no banco de dados
     * @param e Objeto a ser atualizado
     * @author bruno.martins
     * @since 16/05/2014
     * @return GRAVADO, se a gravação for um sucesso; ERRO, se houver qualquer tipo de erro; DUPLICADO, se o arquivo já existir
     */
    public Persistencia atualizar(T e);
    
    /**
     * Valida se um novo objeto não esta duplicado no banco de dados
     * @param novo Objeto a ser validado
     * @author bruno.martins
     * @since 16/05/2014
     * @return True, se for um novo registro; False, se não
     */
    public boolean validaNovoRegistro(T novo);
}
