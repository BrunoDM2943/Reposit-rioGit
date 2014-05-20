package com.tia.view.cadastros;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.tia.controller.CadastrarCursoController;
import com.tia.controller.constantes.Persistencia;

import javax.swing.SpringLayout;

/**
 * Janela responsável por cadastrar um curso
 * 
 * @author Bruno
 * @since 17/05/2014
 * 
 */
public class CadastrarCurso extends JInternalFrame {

    private static final long serialVersionUID = 1L;
    private JPanel panel;
    private JButton btnGravar;
    private JButton btnCancelar;
    private JCheckBox chckbxMatutino;
    private JCheckBox chckbxVesrpertino;
    private JCheckBox chckbxNoturno;
    private SpringLayout springLayout;
    private JTextField tfNome;
    private JTextField tfCodigo;
    private JSpinner spnSemestres;

    public CadastrarCurso() {
	janela();
	componentes();
	eventos();
    }

    private void janela() {
	try {
	    UIManager.setLookAndFeel(new NimbusLookAndFeel());
	} catch (UnsupportedLookAndFeelException ex) {
	    ex.printStackTrace();
	}
	setClosable(true);
	setIconifiable(true);
	setTitle("Cadastrar Curso");
	setBounds(100, 100, 496, 260);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	springLayout = new SpringLayout();
	getContentPane().setLayout(springLayout);

	panel = new JPanel();
	springLayout.putConstraint(SpringLayout.NORTH, panel, 10,
		SpringLayout.NORTH, getContentPane());
	springLayout.putConstraint(SpringLayout.WEST, panel, 10,
		SpringLayout.WEST, getContentPane());
	springLayout.putConstraint(SpringLayout.EAST, panel, 470,
		SpringLayout.WEST, getContentPane());
	panel.setBorder(new TitledBorder(UIManager
		.getBorder("TitledBorder.border"), "Dados do curso",
		TitledBorder.LEADING, TitledBorder.TOP, null, null));
	getContentPane().add(panel);

    }

    public void componentes() {

	btnGravar = new JButton("Gravar");
	springLayout.putConstraint(SpringLayout.WEST, btnGravar, 246,
		SpringLayout.WEST, getContentPane());
	btnGravar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	getContentPane().add(btnGravar);

	btnCancelar = new JButton("Cancelar");
	springLayout.putConstraint(SpringLayout.NORTH, btnGravar, 0,
		SpringLayout.NORTH, btnCancelar);
	springLayout.putConstraint(SpringLayout.EAST, btnGravar, -6,
		SpringLayout.WEST, btnCancelar);
	springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 6,
		SpringLayout.SOUTH, panel);
	springLayout.putConstraint(SpringLayout.WEST, btnCancelar, 361,
		SpringLayout.WEST, getContentPane());
	springLayout.putConstraint(SpringLayout.EAST, btnCancelar, 0,
		SpringLayout.EAST, panel);
	btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 15));
	getContentPane().add(btnCancelar);
	panel.setLayout(new FormLayout(new ColumnSpec[] {
		FormFactory.UNRELATED_GAP_COLSPEC, ColumnSpec.decode("41px"),
		FormFactory.RELATED_GAP_COLSPEC,
		ColumnSpec.decode("67px:grow"), ColumnSpec.decode("115px"),
		ColumnSpec.decode("81px"), ColumnSpec.decode("42px"),
		FormFactory.DEFAULT_COLSPEC, ColumnSpec.decode("65px"),
		FormFactory.RELATED_GAP_COLSPEC, }, new RowSpec[] {
		RowSpec.decode("25px"), FormFactory.RELATED_GAP_ROWSPEC,
		RowSpec.decode("25px"), FormFactory.RELATED_GAP_ROWSPEC,
		FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
		FormFactory.DEFAULT_ROWSPEC, FormFactory.RELATED_GAP_ROWSPEC,
		FormFactory.NARROW_LINE_GAP_ROWSPEC, }));

	JLabel lblCurso = new JLabel("Nome");
	panel.add(lblCurso, "2, 1, right, center");

	tfNome = new JTextField();
	panel.add(tfNome, "4, 1, 4, 1, fill, default");
	tfNome.setColumns(10);

	JLabel lblCodigo = new JLabel("Codigo");
	panel.add(lblCodigo, "2, 3, right, center");

	tfCodigo = new JTextField();
	panel.add(tfCodigo, "4, 3, fill, default");
	tfCodigo.setColumns(10);

	JLabel lblNmeroDeSemestres = new JLabel("Número de Semestres");
	panel.add(lblNmeroDeSemestres, "2, 5, 4, 1");

	spnSemestres = new JSpinner();
	spnSemestres.setModel(new SpinnerNumberModel(new Integer(1),
		new Integer(1), null, new Integer(1)));
	panel.add(spnSemestres, "6, 5");

	JLabel lblTurno = new JLabel("Períodos");
	panel.add(lblTurno, "2, 7, 3, 1, left, center");

	chckbxMatutino = new JCheckBox("Matutino");
	panel.add(chckbxMatutino, "5, 7, left, top");

	chckbxVesrpertino = new JCheckBox("Vesrpertino");
	panel.add(chckbxVesrpertino, "6, 7, 2, 1, left, top");

	chckbxNoturno = new JCheckBox("Noturno");
	panel.add(chckbxNoturno, "8, 7, left, top");
    }

    public void eventos() {
	btnGravar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		
		    Hashtable<String, Object> parametros = new Hashtable<String, Object>();
		    CadastrarCursoController crtl = new CadastrarCursoController();
		    Persistencia resposta  = null;
		    parametros.put("nome", tfNome.getText());
		    parametros.put("codigo", tfCodigo.getText());
		    parametros.put("qtdSemestres", (int) spnSemestres.getValue());
		    parametros.put("matutino",chckbxMatutino.isSelected());
		    parametros.put("vespertino", chckbxVesrpertino.isSelected());
		    parametros.put("noturno", chckbxNoturno.isSelected());

		    if(crtl.validaEntradas(parametros))
			resposta = crtl.persistir(parametros);
		    crtl.validaPersistencia(resposta);
		    crtl = null;
	    }
	});

	btnCancelar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		dispose();
	    }
	});
    }
}
