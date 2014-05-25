package com.tia.view.cadastros;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import alocacaoDinamica.tabelaEspalhamento.TabelaEspalhamento;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.tia.controller.cadastro.CadastrarLocalizacaoController;
import com.tia.controller.constantes.Persistencia;
import com.tia.model.Professor;
import com.tia.model.Status;
import com.tia.view.models.comboBox.ProfessorComboBoxModel;
import com.tia.view.models.comboBox.StatusComboBoxModel;

/**
 * Janela responsável pelo cadastro de localizacao
 * @author Bruno
 * @since 25/05/2014
 * @version 25/05/2014
 */
public class CadastrarLocalizacaoWindow extends JInternalFrame {

	private static final long serialVersionUID = 2732219058262028510L;

	public CadastrarLocalizacaoWindow() {
		setBounds(100, 100, 448, 229);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 128,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 430,
				SpringLayout.WEST, getContentPane());
		panel.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblProfessor = new JLabel("Professor");
		lblProfessor.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblProfessor, "2, 2, left, default");

		final JComboBox<Professor> cbProfessor = new JComboBox<Professor>(
				new ProfessorComboBoxModel());
		cbProfessor.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(cbProfessor, "4, 2, fill, default");

		JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblStatus, "2, 4, left, default");

		final JComboBox<Status> cbStatus = new JComboBox<Status>(
				new StatusComboBoxModel());
		cbStatus.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(cbStatus, "4, 4, fill, default");

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 17));
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 13,
				SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, btnCancelar, 0,
				SpringLayout.EAST, panel);
		getContentPane().add(btnCancelar);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TabelaEspalhamento<String, Object> parametros = new TabelaEspalhamento<String, Object>();
				try {
					parametros.put("professor", cbProfessor.getSelectedItem());
					parametros.put("status", cbStatus.getSelectedItem());
					CadastrarLocalizacaoController crtl = new CadastrarLocalizacaoController();
					Persistencia response = null;
					response = crtl.persistir(parametros);
					crtl.validaPersistencia(response);
				} catch (NullPointerException e1) {
					JOptionPane.showMessageDialog(null,
							"Selecione um item na lista de seleção", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnGravar.setFont(new Font("SansSerif", Font.PLAIN, 17));
		springLayout.putConstraint(SpringLayout.SOUTH, btnGravar, 0,
				SpringLayout.SOUTH, btnCancelar);
		springLayout.putConstraint(SpringLayout.EAST, btnGravar, -6,
				SpringLayout.WEST, btnCancelar);
		getContentPane().add(btnGravar);

	}
}
