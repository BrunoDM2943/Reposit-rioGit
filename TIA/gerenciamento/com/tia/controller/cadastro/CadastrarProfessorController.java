package com.tia.controller.cadastro;

import java.util.List;

import javax.swing.JOptionPane;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;
import alocacaoDinamica.tabelaEspalhamento.TabelaEspalhamento;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.CursoDataAccess;
import com.tia.dao.DocenteDataAccess;
import com.tia.dao.ProfessorDataAccess;
import com.tia.model.Curso;
import com.tia.model.Docente;
import com.tia.model.Professor;

/**
 * Classe responsável por controlar a gravação de professores
 * @author Bruno
 * @since 15/05/2014
 *
 */
public class CadastrarProfessorController implements ValidarCadastro {

    StringBuilder msg = new StringBuilder();

    /**
     * Valida as entradas da janela
     * 
     * @author Bruno
     * @since 04/05/2014
     * @version 15/05/2014
     * @return True se vazia, false se não
     */
    @Override
    public boolean validaEntradas(TabelaEspalhamento<String, Object> parametros) {
	if (parametros.get("nome").toString().isEmpty())
	    msg.append("-Nome do professor \n");
	if (parametros.get("cursos").equals(null))
		msg.append("-Pelo menos um curso deve ser selecionado");
	return msg.toString().isEmpty();
    }

    /**
     * Chama o método para persistir um professor
     * @author bruno.martins
     * @since 15/05/2014
     * @version 17/05/2014
     */
    @SuppressWarnings("unchecked")
	@Override
    public Persistencia persistir(TabelaEspalhamento<String, Object> parametros) {
	ProfessorDataAccess daoProf = new ProfessorDataAccess();
	DocenteDataAccess daoDocente = new DocenteDataAccess();
	Professor prof = new Professor();
	prof.setId();
	prof.setNome(parametros.get("nome").toString());
	Persistencia resposta = daoProf.gravar(prof);
	Docente docente = new Docente();
	docente.setIdDocente();
	docente.setProf(prof);
	ListaEncadeada<Curso> listaCursos = new ListaEncadeada<Curso>();	
	List<Curso> lista = (List<Curso>) parametros.get("cursos"); 
	for(Curso curso : lista)
		listaCursos.addFim(curso);
	docente.setCursos(listaCursos);
	daoDocente.gravar(docente);
	daoProf = null;
	return resposta;	  
    }

    /**
     * Resposta da persistência!
     * @author bruno.martins;
     * @since 15/05/2014
     */
    @Override
    public void validaPersistencia(Persistencia resposta) {
	if(resposta == Persistencia.GRAVADO)
	    JOptionPane.showMessageDialog(null, "O professor foi gravado com sucesso!", "Gravado!", JOptionPane.INFORMATION_MESSAGE);
	if(resposta == Persistencia.ERRO)
	    JOptionPane.showMessageDialog(null, "Erro na gravação!", "Erro!", JOptionPane.ERROR_MESSAGE);
	if(resposta == Persistencia.DUPLICADO)
	    JOptionPane.showMessageDialog(null, "O professor já existe!", "Duplicado!", JOptionPane.WARNING_MESSAGE);
	if(resposta == null)
	    JOptionPane.showMessageDialog(null, msg.toString(),"Erro!",JOptionPane.WARNING_MESSAGE);
    }
    
    /**
     * Carrega o jList com uma lista de cursos
     * @return
     */
    public Curso[] carregajLCursos(){
    	CursoDataAccess dao = new CursoDataAccess();
    	Curso[] vetor = (Curso[])dao.lerTodos().toArray(Curso.class);
    	dao = null;
    	return vetor;
    }
}
