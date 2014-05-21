package com.tia.toten.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

import com.framework.HtmlGenerator;
import com.tia.dao.LocalizacaoDataAccess;

public class LocalizaProfessor extends JFrame {


	private static final long serialVersionUID = -2269238539295676312L;
	private JPanel contentPane;
	private HtmlGenerator htmlGenerator = new HtmlGenerator();
	private String tableGeral;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocalizaProfessor frame = new LocalizaProfessor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LocalizaProfessor() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		tabbedPane.addTab("Instruçoes", null, panel, null);
		tabbedPane.setEnabledAt(0, true);
		panel.setLayout(null);
		
		JLabel lblintrucoes = new JLabel();
		lblintrucoes.setBounds(33, 5, 774, 76);
		lblintrucoes.setText(htmlGenerator.getHtmlFile("instrucoes"));
		panel.add(lblintrucoes);
		
		JLabel lblNewLabel = new JLabel("Voltar");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel.setIcon(new ImageIcon(LocalizaProfessor.class.getResource("/com/sun/java/swing/plaf/windows/icons/HomeFolder.gif")));
		lblNewLabel.setBounds(10, 296, 150, 53);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		
		
		
		panel_1.setBackground(Color.WHITE);
		tabbedPane.addTab("Geral", null, panel_1, null);
		
		final JLabel lblGeral = new JLabel("New label");
		panel_1.add(lblGeral);
		
		carregaLocalizacoes();
		panel_1.addHierarchyListener(new HierarchyListener() {
			public void hierarchyChanged(HierarchyEvent arg0) {
				lblGeral.setText(tableGeral);
			}
		});
	}

	private void carregaLocalizacoes() {
		LocalizacaoDataAccess dao = new LocalizacaoDataAccess();
		tableGeral = dao.geraHtmlLocalizacao();
	}
}
