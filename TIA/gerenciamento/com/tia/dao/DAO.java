package com.tia.dao;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.controller.constantes.Persistencia;

/**
 * Interface responsável por declarar os serviços a serem feitos no banco
 * @author Bruno
 * @since 16/05/2014
 */
public interface DAO<T> {
    
    public Persistencia gravar(T e);
    
    public ListaEncadeada<T> lerTodos();
    
    public Persistencia deletar(T e);
    
    public T buscar(int id);

    public Persistencia atualizar(T e);
    
    public boolean validaNovoRegistro(T novo);
}
