package com.tia.view.gerenciadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import com.tia.controller.TabelaAulaController;
import com.tia.model.Aula;
import com.tia.view.Menu;
import com.tia.view.cadastros.CadastrarAulaWindow;
import com.tia.view.models.table.AulaTableModel;

public class TabelaAulaWindow extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -3982072613396606340L;
    private JTable table;
    private AulaTableModel model;
    private TabelaAulaController crtl = new TabelaAulaController();

    public TabelaAulaWindow() {
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

	JButton btrNovoAula = new JButton("Novo Aula");
	springLayout.putConstraint(SpringLayout.NORTH, btrNovoAula, 6,
		SpringLayout.SOUTH, scrollPane);
	springLayout.putConstraint(SpringLayout.WEST, btrNovoAula, 10,
		SpringLayout.WEST, scrollPane);
	btrNovoAula.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	CadastrarAulaWindow novoAula = new CadastrarAulaWindow();
		novoAula.setVisible(true);
		Menu.desktopPane.add(novoAula);
		Menu.desktopPane.moveToFront(novoAula);
	    }
	});
	getContentPane().add(btrNovoAula);

	JButton btnDeletarAula = new JButton("Deletar Aula");
	springLayout.putConstraint(SpringLayout.WEST, btnDeletarAula, 109, SpringLayout.EAST, btrNovoAula);
	btnDeletarAula.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();

		if (row != -1) {
		    Aula aula= model.getRowAt(row);

		    int resp = JOptionPane.showConfirmDialog(null,
			    "Tem certeza que deseja deletar a aula: \n " + aula
				    + "?");
		    if (resp == 0) {
			crtl.deletarRegistro(aula);
			atualizarModel();
		    }
		} else {
		    JOptionPane.showMessageDialog(null,
			    "Selecione um registro!");
		}

	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnDeletarAula, 0,
		SpringLayout.NORTH, btrNovoAula);
	getContentPane().add(btnDeletarAula);

	JButton btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		dispose();

	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnSair, 0,
		SpringLayout.NORTH, btrNovoAula);
	springLayout.putConstraint(SpringLayout.EAST, btnSair, -10,
		SpringLayout.EAST, getContentPane());
	getContentPane().add(btnSair);

	JButton btnAtualizarLista = new JButton("Atualizar Lista");
	springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarLista, 6,
		SpringLayout.SOUTH, scrollPane);
	springLayout.putConstraint(SpringLayout.WEST, btnAtualizarLista, 6,
		SpringLayout.EAST, btnDeletarAula);
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
