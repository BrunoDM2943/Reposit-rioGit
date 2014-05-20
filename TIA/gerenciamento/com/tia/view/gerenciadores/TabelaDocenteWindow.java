package com.tia.view.gerenciadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import com.tia.controller.TabelaDocenteController;
import com.tia.model.Docente;
import com.tia.view.Menu;
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

		JButton btnNovoDocente = new JButton("Novo Docente");
		springLayout.putConstraint(SpringLayout.NORTH, btnNovoDocente, 6,
				SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnNovoDocente, 10,
				SpringLayout.WEST, scrollPane);
		getContentPane().add(btnNovoDocente);

		JButton btnAtualizarDocente = new JButton("Atualizar docente");
		
		springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarDocente, 0,
				SpringLayout.NORTH, btnNovoDocente);
		springLayout.putConstraint(SpringLayout.WEST, btnAtualizarDocente, 6,
				SpringLayout.EAST, btnNovoDocente);
		getContentPane().add(btnAtualizarDocente);

		JButton btnDeletarDocente = new JButton("Deletar Docente");
		btnDeletarDocente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row != -1) {
					Docente docente = model.getRowAt(row);

					int resp = JOptionPane.showConfirmDialog(null,
							"Tem certeza que deseja deletar o docente " + docente
									+ "?");
					if (resp == 0) {
						crtl.deletarRegistro(docente);
						atualizarModel();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Selecione um registro!");
				}

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnDeletarDocente, 0,
				SpringLayout.NORTH, btnNovoDocente);
		springLayout.putConstraint(SpringLayout.WEST, btnDeletarDocente, 6,
				SpringLayout.EAST, btnAtualizarDocente);
		getContentPane().add(btnDeletarDocente);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSair, 0,
				SpringLayout.NORTH, btnNovoDocente);
		springLayout.putConstraint(SpringLayout.EAST, btnSair, -10,
				SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSair);

		JButton btnAtualizarLista = new JButton("Atualizar Lista");
		springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarLista, 6,
				SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnAtualizarLista, 6,
				SpringLayout.EAST, btnDeletarDocente);
		springLayout.putConstraint(SpringLayout.EAST, btnAtualizarLista, -6,
				SpringLayout.WEST, btnSair);
		btnAtualizarLista.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarModel();
			}
		});
		getContentPane().add(btnAtualizarLista);

	}

	protected void atualizarModel() {
		model = crtl.geraTabela(model);
		table.setModel(model);

	}
}
