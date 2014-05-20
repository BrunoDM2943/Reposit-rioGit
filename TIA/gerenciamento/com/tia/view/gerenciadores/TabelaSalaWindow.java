package com.tia.view.gerenciadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import com.tia.controller.tabela.TabelaSalaController;
import com.tia.model.Sala;
import com.tia.view.Menu;
import com.tia.view.cadastros.CadastrarSala;
import com.tia.view.models.table.SalaTableModel;

public class TabelaSalaWindow extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -3982072613396606340L;
    private JTable table;
    private SalaTableModel model;
    private TabelaSalaController crtl = new TabelaSalaController();

    public TabelaSalaWindow() {
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

	JButton btnNovaSala = new JButton("Nova Sala");
	springLayout.putConstraint(SpringLayout.NORTH, btnNovaSala, 6,
		SpringLayout.SOUTH, scrollPane);
	springLayout.putConstraint(SpringLayout.WEST, btnNovaSala, 10,
		SpringLayout.WEST, scrollPane);
	btnNovaSala.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		CadastrarSala novaSala = new CadastrarSala();
		novaSala.setVisible(true);
		Menu.desktopPane.add(novaSala);
		Menu.desktopPane.moveToFront(novaSala);
	    }
	});
	getContentPane().add(btnNovaSala);

	JButton btnAtualizarSala = new JButton("Atualizar Sala");
	btnAtualizarSala.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();
		if(row != -1) {
		int column = table.getSelectedColumn();
		Sala sala = model.getRowAt(row);
		crtl.atualizarSala(row, column, sala);
		atualizarModel();
		}else {
		    JOptionPane.showMessageDialog(null,
			    "Selecione um registro!");	
		}
	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarSala, 0,
		SpringLayout.NORTH, btnNovaSala);
	springLayout.putConstraint(SpringLayout.WEST, btnAtualizarSala, 6,
		SpringLayout.EAST, btnNovaSala);
	getContentPane().add(btnAtualizarSala);

	JButton btnDeletarSala = new JButton("Deletar Sala");
	btnDeletarSala.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();

		if (row != -1) {
		    Sala sala = model.getRowAt(row);

		    int resp = JOptionPane.showConfirmDialog(null,
			    "Tem certeza que deseja deletar a sala " + sala
				    + "?");
		    if (resp == 0) {
			crtl.deletarRegistro(sala);
			atualizarModel();
		    }
		} else {
		    JOptionPane.showMessageDialog(null,
			    "Selecione um registro!");
		}

	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnDeletarSala, 0,
		SpringLayout.NORTH, btnNovaSala);
	springLayout.putConstraint(SpringLayout.WEST, btnDeletarSala, 6,
		SpringLayout.EAST, btnAtualizarSala);
	getContentPane().add(btnDeletarSala);

	JButton btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		dispose();

	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnSair, 0,
		SpringLayout.NORTH, btnNovaSala);
	springLayout.putConstraint(SpringLayout.EAST, btnSair, -10,
		SpringLayout.EAST, getContentPane());
	getContentPane().add(btnSair);

	JButton btnAtualizarLista = new JButton("Atualizar Lista");
	springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarLista, 6,
		SpringLayout.SOUTH, scrollPane);
	springLayout.putConstraint(SpringLayout.WEST, btnAtualizarLista, 6,
		SpringLayout.EAST, btnDeletarSala);
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
