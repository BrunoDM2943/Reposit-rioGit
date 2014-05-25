package com.tia.view.cadastros;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;

import alocacaoDinamica.tabelaEspalhamento.TabelaEspalhamento;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.tia.controller.cadastro.CadastrarNoticiaController;
import com.tia.controller.constantes.Persistencia;

/**
 * Janela responsável pelo cadastro de noticias
 * @author Bruno
 * @since 25/05/2014
 * @version 25/05/2014
 */
public class CadastrarNoticiaWindow extends JInternalFrame {

	private static final long serialVersionUID = -7966094976810052554L;
	private JTextField tfTitulo;
	
	public CadastrarNoticiaWindow() {
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 558, 341);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);
		
		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, 264, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel, 536, SpringLayout.WEST, getContentPane());
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
				RowSpec.decode("default:grow"),}));
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblTitulo, "2, 2, right, default");
		
		tfTitulo = new JTextField();
		panel.add(tfTitulo, "4, 2, fill, top");
		tfTitulo.setColumns(10);
		
		JLabel lblNotcia = new JLabel("Notícia");
		lblNotcia.setFont(new Font("SansSerif", Font.PLAIN, 17));
		panel.add(lblNotcia, "2, 4");
		
		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, "4, 4, fill, fill");
		
		final JTextArea tpNoticia = new JTextArea();
		scrollPane.setViewportView(tpNoticia);
		tpNoticia.setLineWrap(true);
		tpNoticia.setWrapStyleWord(true);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancelar.setFont(new Font("SansSerif", Font.PLAIN, 17));
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 2, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, btnCancelar, -10, SpringLayout.EAST, getContentPane());
		getContentPane().add(btnCancelar);
		
		JButton btnGravar = new JButton("Gravar");
		springLayout.putConstraint(SpringLayout.NORTH, btnGravar, 0, SpringLayout.NORTH, btnCancelar);
		springLayout.putConstraint(SpringLayout.EAST, btnGravar, -6, SpringLayout.WEST, btnCancelar);
		btnGravar.setFont(new Font("SansSerif", Font.PLAIN, 17));
		getContentPane().add(btnGravar);
		btnGravar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				TabelaEspalhamento<String, Object> parametros = new TabelaEspalhamento<String, Object>();
				parametros.put("titulo", tfTitulo.getText());
				parametros.put("noticia", tpNoticia.getText());				
				CadastrarNoticiaController crtl = new CadastrarNoticiaController();
				Persistencia response = null;
				if(crtl.validaEntradas(parametros))
					response = crtl.persistir(parametros);
				crtl.validaPersistencia(response);
				
			}
		});

	}
}
