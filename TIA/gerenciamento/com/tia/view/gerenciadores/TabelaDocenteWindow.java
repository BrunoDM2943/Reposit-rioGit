package com.tia.view.gerenciadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import com.tia.controller.tabela.TabelaDocenteController;
import com.tia.view.models.table.DocenteTableModel;

public class TabelaDocenteWindow extends JInternalFrame {
	/**
     * 
     */
	private static final long serialVersionUID = -3982072613396606340L;
	private JTable table;
	private DocenteTableModel model;
	private TabelaDocenteController crtl = new TabelaDocenteController();

	public TabelaDocenteWindow() {
		setMaximizable(true);
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 506, 382);
		SpringLayout springLayout = new SpringLayout();
		getContentPane().setLayout(springLayout);

		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 0,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 0,
				SpringLayout.WEST, getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 279,
				SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 0,
				SpringLayout.EAST, getContentPane());
		getContentPane().add(scrollPane);

		table = new JTable();
		model = crtl.geraTabela(model);
		table.setModel(model);
		
		scrollPane.setViewportView(table);

		JButton btnSair = new JButton("Sair");
		springLayout.putConstraint(SpringLayout.NORTH, btnSair, 6, SpringLayout.SOUTH, scrollPane);
		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		springLayout.putConstraint(SpringLayout.EAST, btnSair, -10,
				SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSair);

	}
}
