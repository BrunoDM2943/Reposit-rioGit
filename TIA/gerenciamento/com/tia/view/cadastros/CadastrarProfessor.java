package com.tia.view.cadastros;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.tia.controller.CadastrarProfessorController;
import com.tia.controller.constantes.Persistencia;
import com.tia.dao.CursoDAO;
import com.tia.model.Curso;

public class CadastrarProfessor extends JInternalFrame {

    private static final long serialVersionUID = 19L;
    private JTextField tfNome;
    private JPanel panel;
    private JButton btnCadastrar;
    private JButton btnNewButton;
    private JList<Curso> jLCursos; 

    public CadastrarProfessor() {	
	janela();
	componentes();
	eventos();
    }

    private void janela() {
	
	
	setVisible(true);
	setIconifiable(true);
	setClosable(true);
	getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 17));
	setFont(new Font("Dialog", Font.PLAIN, 11));
	setTitle("Cadastrar Professor");
	setBounds(100, 100, 415, 271);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	getContentPane().setLayout(null);

	panel = new JPanel();
	panel.setBounds(7, 7, 391, 164);
	panel.setBorder(new TitledBorder(new CompoundBorder(new LineBorder(
		new Color(160, 160, 160)), new EmptyBorder(2, 2, 2, 2)),
		"Dados pessoais", TitledBorder.LEADING, TitledBorder.TOP, null,
		null));
	getContentPane().add(panel);
    }

    private void componentes() {
	panel.setLayout(new FormLayout(new ColumnSpec[] {
			FormFactory.RELATED_GAP_COLSPEC,
			FormFactory.DEFAULT_COLSPEC,
			FormFactory.UNRELATED_GAP_COLSPEC,
			ColumnSpec.decode("304px:grow"),},
		new RowSpec[] {
			FormFactory.RELATED_GAP_ROWSPEC,
			FormFactory.DEFAULT_ROWSPEC,
			FormFactory.RELATED_GAP_ROWSPEC,
			RowSpec.decode("default:grow"),
			FormFactory.PARAGRAPH_GAP_ROWSPEC,
			RowSpec.decode("26px"),}));
		JLabel lblNome = new JLabel("Nome");
		panel.add(lblNome, "2, 2, fill, top");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 17));
		
			tfNome = new JTextField();
			panel.add(tfNome, "4, 2, fill, fill");
			tfNome.setColumns(10);
			
			JLabel lblCursos = new JLabel("Cursos");
			lblCursos.setFont(new Font("SansSerif", Font.PLAIN, 17));
			panel.add(lblCursos, "2, 4");
			
			jLCursos = new JList<Curso>(new DefaultListModel<Curso>());
			CursoDAO dao = new CursoDAO();
			jLCursos.setListData((Curso[])dao.lerTodos().toArray(Curso.class));
			panel.add(jLCursos, "4, 4, 1, 3, fill, fill");

	btnCadastrar = new JButton("Gravar");

	btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 17));
	btnCadastrar.setBounds(178, 184, 104, 25);
	getContentPane().add(btnCadastrar);

	btnNewButton = new JButton("Cancelar");
	btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
	btnNewButton.setBounds(294, 184, 104, 25);

	getContentPane().add(btnNewButton);
    }

    public void eventos() {
	btnCadastrar.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		CadastrarProfessorController crtl = new CadastrarProfessorController();
		Hashtable<String, Object> parametros = new Hashtable<String, Object>();
		Persistencia resposta = null;
		parametros.put("nome", tfNome.getText());
		parametros.put("cursos", jLCursos.getSelectedValuesList());
		if(crtl.validaEntradas(parametros))
		    resposta = crtl.persistir(parametros);
		crtl.validaPersistencia(resposta);
		crtl = null;		
	    }
	});

	btnNewButton.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		dispose();
	    }
	});

    }
}
