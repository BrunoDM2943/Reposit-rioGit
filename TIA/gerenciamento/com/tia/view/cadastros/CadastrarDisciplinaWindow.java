package com.tia.view.cadastros;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.tia.controller.cadastro.CadastrarDisciplinaController;
import com.tia.controller.constantes.Persistencia;
import com.tia.model.Curso;
import com.tia.view.models.comboBox.CursoComboBoxModel;
import com.tia.view.models.comboBox.SemestreComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Hashtable;

public class CadastrarDisciplinaWindow extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4538000557143755280L;
	private JTextField tfNome;

	/**
	 * Create the frame.
	 */
	public CadastrarDisciplinaWindow() {
		setTitle("Cadastro de Disciplina");
		setVisible(true);
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 576, 236);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 157,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 556,
				SpringLayout.WEST, getContentPane());
		panel.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC, FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), }, new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC, FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblNome, "2, 2, left, default");

		tfNome = new JTextField();
		panel.add(tfNome, "4, 2, fill, default");
		tfNome.setColumns(10);

		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblCurso, "2, 4, left, default");

		@SuppressWarnings("unchecked")
		final JComboBox<Curso> cbCurso = new JComboBox<Curso>(
				new CursoComboBoxModel());

		cbCurso.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(cbCurso, "4, 4, fill, default");

		JLabel lblSemestre = new JLabel("Semestre");
		lblSemestre.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblSemestre, "2, 6, right, default");

		final JComboBox<Integer> cbSemestre = new JComboBox<Integer>();
		cbSemestre.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(cbSemestre, "4, 6, fill, default");
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 17));
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, btnCancelar, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnCancelar);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//FIXME hashTable
				Hashtable<String, Object> parametros = new Hashtable<String, Object>();
				parametros.put("nome", tfNome.getText());
				parametros.put("curso", cbCurso.getSelectedItem());
				parametros.put("semestre", cbSemestre.getSelectedItem());
				CadastrarDisciplinaController crtl = new CadastrarDisciplinaController();
				Persistencia response = null;
				if(crtl.validaEntradas(parametros))
					response = crtl.persistir(parametros);
				crtl.validaPersistencia(response);
				crtl = null;
			}
		});
		btnGravar.setFont(new Font("SansSerif", Font.PLAIN, 17));
		springLayout.putConstraint(SpringLayout.NORTH, btnGravar, 6, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, btnGravar, -6, SpringLayout.WEST, btnCancelar);
		getContentPane().add(btnGravar);

		cbCurso.addItemListener(new ItemListener() {
			@SuppressWarnings("unchecked")
			public void itemStateChanged(ItemEvent arg0) {
				cbSemestre.setModel(new SemestreComboBoxModel((Curso) cbCurso
						.getSelectedItem()));
				cbSemestre.setSelectedIndex(0);
			}
		});
	}
}
