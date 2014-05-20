package com.tia.view.gerenciadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import com.tia.controller.tabela.TabelaCursoController;
import com.tia.model.Curso;
import com.tia.view.Menu;
import com.tia.view.cadastros.CadastrarCurso;
import com.tia.view.models.table.CursoTableModel;

public class TabelaCursoWindow extends JInternalFrame {
	/**
     * 
     */
	private static final long serialVersionUID = -3982072613396606340L;
	private JTable table;
	private CursoTableModel model;
	private TabelaCursoController crtl = new TabelaCursoController();

	public TabelaCursoWindow() {
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

		JButton btnNovoCurso = new JButton("Novo Curso");
		springLayout.putConstraint(SpringLayout.NORTH, btnNovoCurso, 6,
				SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnNovoCurso, 10,
				SpringLayout.WEST, scrollPane);
		btnNovoCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarCurso novoCurso = new CadastrarCurso();
				novoCurso.setVisible(true);
				Menu.desktopPane.add(novoCurso);
				Menu.desktopPane.moveToFront(novoCurso);
			}
		});
		getContentPane().add(btnNovoCurso);

		JButton btnAtualizarCurso = new JButton("Atualizar curso");
		btnAtualizarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					int column = table.getSelectedColumn();
					Curso curso = model.getRowAt(row);
					crtl.atualizaCurso(row, column, curso);
					atualizarModel();
				} else {
					JOptionPane.showMessageDialog(null,
							"Selecione um registro!");
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarCurso, 0,
				SpringLayout.NORTH, btnNovoCurso);
		springLayout.putConstraint(SpringLayout.WEST, btnAtualizarCurso, 6,
				SpringLayout.EAST, btnNovoCurso);
		getContentPane().add(btnAtualizarCurso);

		JButton btnDeletarCurso = new JButton("Deletar Curso");
		btnDeletarCurso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row != -1) {
					Curso curso = model.getRowAt(row);

					int resp = JOptionPane.showConfirmDialog(null,
							"Tem certeza que deseja deletar o curso " + curso
									+ "?");
					if (resp == 0) {
						crtl.deletarRegistro(curso);
						atualizarModel();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Selecione um registro!");
				}

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnDeletarCurso, 0,
				SpringLayout.NORTH, btnNovoCurso);
		springLayout.putConstraint(SpringLayout.WEST, btnDeletarCurso, 6,
				SpringLayout.EAST, btnAtualizarCurso);
		getContentPane().add(btnDeletarCurso);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSair, 0,
				SpringLayout.NORTH, btnNovoCurso);
		springLayout.putConstraint(SpringLayout.EAST, btnSair, -10,
				SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSair);

		JButton btnAtualizarLista = new JButton("Atualizar Lista");
		springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarLista, 6,
				SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnAtualizarLista, 6,
				SpringLayout.EAST, btnDeletarCurso);
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
