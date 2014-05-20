package com.tia.controller;

import java.util.Hashtable;

import com.tia.controller.constantes.Persistencia;

/**
 * Serviço de validações
 * @author bruno.martins
 * @since 15/05/2014
 *
 */
public interface ValidarCadastro {

    public boolean validaEntradas(Hashtable<String, Object> parametros);

    public Persistencia persistir(Hashtable<String, Object> parametros);

    public void validaPersistencia(Persistencia response);

}
