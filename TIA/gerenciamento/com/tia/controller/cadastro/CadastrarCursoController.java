package com.tia.controller.cadastro;

import java.util.Hashtable;

import javax.swing.JOptionPane;

import com.tia.controller.constantes.Persistencia;
import com.tia.dao.CursoDAO;
import com.tia.model.Curso;

/**
 * Classe responsável por controlar a gravação de um curso
 * 
 * @author bruno.martins
 * @since 15/05/2014
 */
public class CadastrarCursoController implements ValidarCadastro {

	StringBuilder msg = new StringBuilder();

	@Override
	public boolean validaEntradas(Hashtable<String, Object> parametros) {
		if (parametros.get("nome").toString().isEmpty())
			msg.append("-O curso deve conter um nome \n");

		if (parametros.get("codigo").toString().isEmpty())
			msg.append("-O curso deve conter um código \n");

		if (!((boolean) parametros.get("matutino")
				|| (boolean) parametros.get("vespertino") || (boolean) parametros
					.get("noturno")))
			msg.append("-Pelo menos um período deve ser selecionado \n");
		return msg.toString().isEmpty();
	}

	@Override
	public Persistencia persistir(Hashtable<String, Object> parametros) {
		CursoDAO dao = new CursoDAO();
		Curso curso = new Curso();
		curso.setNome(parametros.get("nome").toString());
		curso.setCodigo(parametros.get("codigo").toString());
		curso.setQtdSemestres((int) parametros.get("qtdSemestres"));
		Persistencia resposta = null;
		curso.setIdCurso();
		curso.setTurnos("matutino", (boolean) parametros.get("matutino"));
		curso.setTurnos("vespertino", (boolean) parametros.get("vespertino"));
		curso.setTurnos("noturno", (boolean) parametros.get("noturno"));
		resposta = dao.gravar(curso);
		dao = null;
		return resposta;
	}

	@Override
	public void validaPersistencia(Persistencia resposta) {
		if (resposta == Persistencia.GRAVADO)
			JOptionPane.showMessageDialog(null,
					"O curso foi gravado com sucesso!", "Gravado!",
					JOptionPane.INFORMATION_MESSAGE);
		if (resposta == Persistencia.ERRO)
			JOptionPane.showMessageDialog(null, "Erro na gravação!", "Erro!",
					JOptionPane.ERROR_MESSAGE);
		if (resposta == Persistencia.DUPLICADO)
			JOptionPane.showMessageDialog(null, "O curso já existe!",
					"Duplicado!", JOptionPane.WARNING_MESSAGE);
		if (resposta == null)
			JOptionPane.showMessageDialog(null, msg.toString(), "Erro!",
					JOptionPane.WARNING_MESSAGE);

	}

}
