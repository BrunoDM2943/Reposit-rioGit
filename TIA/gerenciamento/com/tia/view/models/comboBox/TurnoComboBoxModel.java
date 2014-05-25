package com.tia.view.models.comboBox;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import alocacaoDinamica.listaEncadeada.ListaEncadeada;

import com.tia.controller.constantes.Turno;
import com.tia.model.Curso;

/**
 * Classe responsável pelo controle do comboBox de Turnos
 * @author Bruno
 * @since 25/05/2014
 * @version 25/05/2014
 *
 */
public class TurnoComboBoxModel extends AbstractListModel<Turno> implements ComboBoxModel<Turno>{

	private static final long serialVersionUID = 4196829445711604680L;
	Turno[] turnos;
	Turno selection = null;

	public TurnoComboBoxModel(Curso curso) {
		ListaEncadeada<Turno> lista = new ListaEncadeada<Turno>();
		
		if(curso.getTurnos().get("matutino"))
			lista.addFim(Turno.M);
		if(curso.getTurnos().get("vespertino"))
			lista.addFim(Turno.V);
		if(curso.getTurnos().get("noturno"))
			lista.addFim(Turno.N);
		turnos = (Turno[]) lista.toArray(Turno.class);
	}

	@Override
	public Turno getElementAt(int arg0) {
		return turnos[arg0];
	}

	@Override
	public int getSize() {
		return turnos.length;
	}

	@Override
	public Object getSelectedItem() {
		return selection;
	}

	@Override
	public void setSelectedItem(Object anItem) {
		this.selection = (Turno) anItem;
		
	}

}
