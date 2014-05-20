package com.tia.view.gerenciadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import com.tia.controller.TabelaProfessorController;
import com.tia.model.Professor;
import com.tia.view.Menu;
import com.tia.view.cadastros.CadastrarProfessor;
import com.tia.view.models.table.ProfessorTableModel;

public class TabelaProfessorWindow extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -3982072613396606340L;
    private JTable table;
    private ProfessorTableModel model;
    private TabelaProfessorController crtl = new TabelaProfessorController();

    public TabelaProfessorWindow() {
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

	JButton btrNovoProfessor = new JButton("Novo Professor");
	springLayout.putConstraint(SpringLayout.NORTH, btrNovoProfessor, 6,
		SpringLayout.SOUTH, scrollPane);
	springLayout.putConstraint(SpringLayout.WEST, btrNovoProfessor, 10,
		SpringLayout.WEST, scrollPane);
	btrNovoProfessor.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		CadastrarProfessor novoProfessor = new CadastrarProfessor();
		novoProfessor.setVisible(true);
		Menu.desktopPane.add(novoProfessor);
		Menu.desktopPane.moveToFront(novoProfessor);
	    }
	});
	getContentPane().add(btrNovoProfessor);

	JButton btnAtualizarProfessor = new JButton("Atualizar professor");
	btnAtualizarProfessor.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();
		if(row != -1) {
		int column = table.getSelectedColumn();
		Professor prof = model.getRowAt(row);
		crtl.atualizarSala(row, column, prof);
		atualizarModel();
		}else {
		    JOptionPane.showMessageDialog(null,
			    "Selecione um registro!");	
		}
	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarProfessor, 0,
		SpringLayout.NORTH, btrNovoProfessor);
	springLayout.putConstraint(SpringLayout.WEST, btnAtualizarProfessor, 6,
		SpringLayout.EAST, btrNovoProfessor);
	getContentPane().add(btnAtualizarProfessor);

	JButton btnDeletarProfessor = new JButton("Deletar Professor");
	btnDeletarProfessor.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();

		if (row != -1) {
		    Professor prof= model.getRowAt(row);

		    int resp = JOptionPane.showConfirmDialog(null,
			    "Tem certeza que deseja deletar o professor " + prof
				    + "?");
		    if (resp == 0) {
			crtl.deletarRegistro(prof);
			atualizarModel();
		    }
		} else {
		    JOptionPane.showMessageDialog(null,
			    "Selecione um registro!");
		}

	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnDeletarProfessor, 0,
		SpringLayout.NORTH, btrNovoProfessor);
	springLayout.putConstraint(SpringLayout.WEST, btnDeletarProfessor, 6,
		SpringLayout.EAST, btnAtualizarProfessor);
	getContentPane().add(btnDeletarProfessor);

	JButton btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		dispose();

	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnSair, 0,
		SpringLayout.NORTH, btrNovoProfessor);
	springLayout.putConstraint(SpringLayout.EAST, btnSair, -10,
		SpringLayout.EAST, getContentPane());
	getContentPane().add(btnSair);

	JButton btnAtualizarLista = new JButton("Atualizar Lista");
	springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarLista, 6,
		SpringLayout.SOUTH, scrollPane);
	springLayout.putConstraint(SpringLayout.WEST, btnAtualizarLista, 6,
		SpringLayout.EAST, btnDeletarProfessor);
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
