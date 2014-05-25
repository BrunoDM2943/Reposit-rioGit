package com.tia.view.cadastros;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import alocacaoDinamica.tabelaEspalhamento.TabelaEspalhamento;

import com.framework.Dia;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.tia.controller.cadastro.CadastrarAulaController;
import com.tia.controller.constantes.Persistencia;
import com.tia.controller.constantes.Turno;
import com.tia.model.Curso;
import com.tia.model.Disciplina;
import com.tia.model.Professor;
import com.tia.model.Sala;
import com.tia.view.models.comboBox.CursoComboBoxModel;
import com.tia.view.models.comboBox.DisciplinaComboBoxModel;
import com.tia.view.models.comboBox.ProfessorComboBoxModel;
import com.tia.view.models.comboBox.SalaComboBoxModel;
import com.tia.view.models.comboBox.TurnoComboBoxModel;

public class CadastrarAulaWindow extends JInternalFrame {


	private static final long serialVersionUID = 8232389019523536744L;
	private JFormattedTextField tfIni;
	private JFormattedTextField tfFim;



	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public CadastrarAulaWindow() {
		setBounds(100, 100, 545, 488);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		panel.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(panel);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblCurso = new JLabel("Curso");
		lblCurso.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblCurso, "2, 2, left, default");
		
		final JComboBox<Curso> cbCurso = new JComboBox<Curso>(new CursoComboBoxModel());		
		cbCurso.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(cbCurso, "4, 2, fill, default");
		
		JLabel lblMateria = new JLabel("Materia");
		lblMateria.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblMateria, "2, 4, left, default");
		
		final JComboBox<Disciplina> cbDisciplina = new JComboBox<Disciplina>();
		cbDisciplina.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(cbDisciplina, "4, 4, fill, default");
		
		JLabel lblProfessor = new JLabel("Professor");
		lblProfessor.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblProfessor, "2, 6, left, default");
		
		final JComboBox<Professor> cbProfessor = new JComboBox<Professor>();
		cbProfessor.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(cbProfessor, "4, 6, fill, default");
		
		JLabel lblTurno = new JLabel("Turno");
		lblTurno.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblTurno, "2, 8, left, default");
		
		final JComboBox<Turno> cbTurno = new JComboBox<Turno>();
		cbTurno.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(cbTurno, "4, 8, fill, default");
		
		JLabel lblDiaDaSemana = new JLabel("Dia da Semana");
		lblDiaDaSemana.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblDiaDaSemana, "2, 10, right, default");
		
		final JComboBox<Dia> cbDia = new JComboBox<Dia>();
		cbDia.setFont(new Font("SansSerif", Font.PLAIN, 17));
		cbDia.setModel(new DefaultComboBoxModel<Dia>(Dia.values()));
		panel.add(cbDia, "4, 10, fill, default");
		
		JLabel lblSala = new JLabel("Sala");
		lblSala.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblSala, "2, 12, left, default");
		
		final JComboBox<Sala> cbSala = new JComboBox<Sala>(new SalaComboBoxModel());
		cbSala.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(cbSala, "4, 12, fill, default");
		
		JLabel lblIncio = new JLabel("Início");
		lblIncio.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblIncio, "2, 14, left, default");
		
		try {
			tfIni = new JFormattedTextField(new MaskFormatter("##:##"));
			tfIni.setFont(new Font("SansSerif", Font.PLAIN, 17));
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		panel.add(tfIni, "4, 14, left, default");
		tfIni.setColumns(5);
		
		JLabel lblFim = new JLabel("Fim");
		lblFim.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblFim, "2, 16, left, default");
		
		try {
			tfFim = new JFormattedTextField(new MaskFormatter("##:##"));
			tfFim.setFont(new Font("SansSerif", Font.PLAIN, 17));
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		panel.add(tfFim, "4, 16, left, top");
		tfFim.setColumns(5);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		springLayout.putConstraint(SpringLayout.EAST, panel, 0, SpringLayout.EAST, btnCancelar);
		springLayout.putConstraint(SpringLayout.EAST, btnCancelar, -10, SpringLayout.EAST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -6, SpringLayout.NORTH, btnCancelar);
		springLayout.putConstraint(SpringLayout.SOUTH, btnCancelar, -30, SpringLayout.SOUTH, getContentPane());
		btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 17));
		getContentPane().add(btnCancelar);
		
		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				//FIXME HashTable
				TabelaEspalhamento<String, Object> parametros = new TabelaEspalhamento<String, Object>();
				parametros.put("curso", (Curso)cbCurso.getSelectedItem());
				parametros.put("disciplina", (Disciplina) cbDisciplina.getSelectedItem());
				parametros.put("professor", (Professor) cbProfessor.getSelectedItem());
				parametros.put("turno", (Turno) cbTurno.getSelectedItem());
				parametros.put("sala", (Sala) cbSala.getSelectedItem());
				parametros.put("inicio", tfIni.getText());
				parametros.put("fim", tfFim.getText());
				parametros.put("dia", cbDia.getSelectedItem());
				CadastrarAulaController crtl = new CadastrarAulaController();
				Persistencia response = null;
				if(crtl.validaEntradas(parametros))
					response = crtl.persistir(parametros);
				crtl.validaPersistencia(response);
				
				
				
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnGravar, 0, SpringLayout.NORTH, btnCancelar);
		springLayout.putConstraint(SpringLayout.EAST, btnGravar, -6, SpringLayout.WEST, btnCancelar);
		btnGravar.setFont(new Font("SansSerif", Font.PLAIN, 17));
		getContentPane().add(btnGravar);

		cbCurso.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				cbDisciplina.setModel(new DisciplinaComboBoxModel((Curso)cbCurso.getSelectedItem()));
				cbDisciplina.setSelectedIndex(0);
				cbProfessor.setModel(new ProfessorComboBoxModel((Curso)cbCurso.getSelectedItem()));
				cbProfessor.setSelectedIndex(0);
				cbTurno.setModel(new TurnoComboBoxModel((Curso)(cbCurso.getSelectedItem())));
				cbTurno.setSelectedIndex(0);
			}
		});
		
	}
	
	
	
}
