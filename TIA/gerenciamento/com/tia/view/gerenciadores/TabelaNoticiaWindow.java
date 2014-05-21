package com.tia.view.gerenciadores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpringLayout;

import com.tia.controller.tabela.TabelaNoticiaController;
import com.tia.model.Noticia;
import com.tia.view.Menu;
import com.tia.view.cadastros.CadastrarNoticiaWindow;
import com.tia.view.models.table.NoticiaTableModel;

public class TabelaNoticiaWindow extends JInternalFrame {
    /**
     * 
     */
    private static final long serialVersionUID = -3982072613396606340L;
    private JTable table;
    private NoticiaTableModel model;
    private TabelaNoticiaController crtl = new TabelaNoticiaController();

    public TabelaNoticiaWindow() {
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

	JButton btrNovoNoticia = new JButton("Novo Noticia");
	springLayout.putConstraint(SpringLayout.NORTH, btrNovoNoticia, 6,
		SpringLayout.SOUTH, scrollPane);
	springLayout.putConstraint(SpringLayout.WEST, btrNovoNoticia, 10,
		SpringLayout.WEST, scrollPane);
	btrNovoNoticia.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	CadastrarNoticiaWindow novoNoticia = new CadastrarNoticiaWindow();
		novoNoticia.setVisible(true);
		Menu.desktopPane.add(novoNoticia);
		Menu.desktopPane.moveToFront(novoNoticia);
	    }
	});
	getContentPane().add(btrNovoNoticia);

	JButton btnDeletarNoticia = new JButton("Deletar Noticia");
	springLayout.putConstraint(SpringLayout.WEST, btnDeletarNoticia, 109, SpringLayout.EAST, btrNovoNoticia);
	btnDeletarNoticia.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
		int row = table.getSelectedRow();

		if (row != -1) {
		    Noticia noticia= model.getRowAt(row);

		    int resp = JOptionPane.showConfirmDialog(null,
			    "Tem certeza que deseja deletar a noticia: \n " + noticia
				    + "?");
		    if (resp == 0) {
			crtl.deletarRegistro(noticia);
			atualizarModel();
		    }
		} else {
		    JOptionPane.showMessageDialog(null,
			    "Selecione um registro!");
		}

	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnDeletarNoticia, 0,
		SpringLayout.NORTH, btrNovoNoticia);
	getContentPane().add(btnDeletarNoticia);

	JButton btnSair = new JButton("Sair");
	btnSair.addActionListener(new ActionListener() {

	    @Override
	    public void actionPerformed(ActionEvent arg0) {
		dispose();

	    }
	});
	springLayout.putConstraint(SpringLayout.NORTH, btnSair, 0,
		SpringLayout.NORTH, btrNovoNoticia);
	springLayout.putConstraint(SpringLayout.EAST, btnSair, -10,
		SpringLayout.EAST, getContentPane());	
	getContentPane().add(btnSair);

	JButton btnAtualizarLista = new JButton("Atualizar Lista");
	springLayout.putConstraint(SpringLayout.NORTH, btnAtualizarLista, 6,
		SpringLayout.SOUTH, scrollPane);
	springLayout.putConstraint(SpringLayout.WEST, btnAtualizarLista, 6,
		SpringLayout.EAST, btnDeletarNoticia);
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
