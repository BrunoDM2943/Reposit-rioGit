package com.tia.view.gerenciadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import com.tia.controller.tabela.TabelaDisciplinaController;
import com.tia.model.Disciplina;
import com.tia.view.Menu;
import com.tia.view.cadastros.CadastrarCurso;
import com.tia.view.models.table.DisciplinaTableModel;

/**
 * Janela responsável pela gerenciamento de disciplina
 * @author Bruno
 * @since 25/05/2014
 * @version 25/05/2014
 */
public class TabelaDisciplinaWindow extends JInternalFrame {
	/**
     * 
     */
	private static final long serialVersionUID = -3982072613396606340L;
	private JTable table;
	private DisciplinaTableModel model;
	private TabelaDisciplinaController crtl = new TabelaDisciplinaController();

	public TabelaDisciplinaWindow() {
		setMaximizable(true);
		setIconifiable(true);
		setResizable(true);
		setClosable(true);
		setBounds(100, 100, 665, 382);
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
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);

		JButton btnNovaDisciplina = new JButton("Novo Disciplina");
		springLayout.putConstraint(SpringLayout.NORTH, btnNovaDisciplina, 6,
				SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnNovaDisciplina, 10,
				SpringLayout.WEST, scrollPane);
		btnNovaDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarCurso novoCurso = new CadastrarCurso();
				novoCurso.setVisible(true);
				Menu.desktopPane.add(novoCurso);
				Menu.desktopPane.moveToFront(novoCurso);
			}
		});
		getContentPane().add(btnNovaDisciplina);

		JButton btnAtualizarDisciplina = new JButton("Atualizar Disciplina");
		btnAtualizarDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if (row != -1) {
					int column = table.getSelectedColumn();
					Disciplina disc = model.getRowAt(row);
					crtl.atualizaDisciplina(row, column, disc);
					atualizarModel();
				} else {
					JOptionPane.showMessageDialog(null,
							"Selecione um registro!");
				}
			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarDisciplina, 0,
				SpringLayout.NORTH, btnNovaDisciplina);
		springLayout.putConstraint(SpringLayout.WEST, btnAtualizarDisciplina, 6,
				SpringLayout.EAST, btnNovaDisciplina);
		getContentPane().add(btnAtualizarDisciplina);

		JButton btnDeletarDisciplina = new JButton("Deletar Disciplina");
		btnDeletarDisciplina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();

				if (row != -1) {
					Disciplina disc = model.getRowAt(row);

					int resp = JOptionPane.showConfirmDialog(null,
							"Tem certeza que deseja deletar a disciplina " + disc
									+ "?");
					if (resp == 0) {
						crtl.deletarRegistro(disc);
						atualizarModel();
					}
				} else {
					JOptionPane.showMessageDialog(null,
							"Selecione um registro!");
				}

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnDeletarDisciplina, 0,
				SpringLayout.NORTH, btnNovaDisciplina);
		springLayout.putConstraint(SpringLayout.WEST, btnDeletarDisciplina, 6,
				SpringLayout.EAST, btnAtualizarDisciplina);
		getContentPane().add(btnDeletarDisciplina);

		JButton btnSair = new JButton("Sair");
		btnSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();

			}
		});
		springLayout.putConstraint(SpringLayout.NORTH, btnSair, 0,
				SpringLayout.NORTH, btnNovaDisciplina);
		springLayout.putConstraint(SpringLayout.EAST, btnSair, -10,
				SpringLayout.EAST, getContentPane());
		getContentPane().add(btnSair);

		JButton btnAtualizarLista = new JButton("Atualizar Lista");
		springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarLista, 6,
				SpringLayout.SOUTH, scrollPane);
		springLayout.putConstraint(SpringLayout.WEST, btnAtualizarLista, 6,
				SpringLayout.EAST, btnDeletarDisciplina);
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
