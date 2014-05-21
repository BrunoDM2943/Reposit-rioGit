package com.tia.view.gerenciadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import com.tia.controller.tabela.TabelaLocalizacaoController;
import com.tia.model.Localizacao;
import com.tia.view.Menu;
import com.tia.view.cadastros.CadastrarLocalizacaoWindow;
import com.tia.view.models.table.LocalizacaoTableModel;

public class TabelaLocalizacaoWindow extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -3982072613396606340L;
    private JTable table;
    private LocalizacaoTableModel model;
    private TabelaLocalizacaoController crtl = new TabelaLocalizacaoController();

    public TabelaLocalizacaoWindow() {
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

	JButton btrNovoLocalizacao = new JButton("Novo Localizacao");
	springLayout.putConstraint(SpringLayout.NORTH, btrNovoLocalizacao, 6,
		SpringLayout.SOUTH, scrollPane);
	springLayout.putConstraint(SpringLayout.WEST, btrNovoLocalizacao, 10,
		SpringLayout.WEST, scrollPane);
	btrNovoLocalizacao.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	CadastrarLocalizacaoWindow novoLocalizacao = new CadastrarLocalizacaoWindow();
		novoLocalizacao.setVisible(true);
		Menu.desktopPane.add(novoLocalizacao);
		Menu.desktopPane.moveToFront(novoLocalizacao);
	    }
	});
	getContentPane().add(btrNovoLocalizacao);

	JButton btnDeletarLocalizacao = new JButton("Deletar Localizacao");
	springLayout.putConstraint(SpringLayout.WEST, btnDeletarLocalizacao, 109, SpringLayout.EAST, btrNovoLocalizacao);
	btnDeletarLocalizacao.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();

		if (row != -1) {
		    Localizacao localizacao= model.getRowAt(row);

		    int resp = JOptionPane.showConfirmDialog(null,
			    "Tem certeza que deseja deletar a localizacao: \n " + localizacao
				    + "?");
		    if (resp == 0) {
			crtl.deletarRegistro(localizacao);
			atualizarModel();
		    }
		} else {
		    JOptionPane.showMessageDialog(null,
			    "Selecione um registro!");
		}

	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnDeletarLocalizacao, 0,
		SpringLayout.NORTH, btrNovoLocalizacao);
	getContentPane().add(btnDeletarLocalizacao);

	JButton btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		dispose();

	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnSair, 0,
		SpringLayout.NORTH, btrNovoLocalizacao);
	springLayout.putConstraint(SpringLayout.EAST, btnSair, -10,
		SpringLayout.EAST, getContentPane());
	getContentPane().add(btnSair);

	JButton btnAtualizarLista = new JButton("Atualizar Lista");
	springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarLista, 6,
		SpringLayout.SOUTH, scrollPane);
	springLayout.putConstraint(SpringLayout.WEST, btnAtualizarLista, 6,
		SpringLayout.EAST, btnDeletarLocalizacao);
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
