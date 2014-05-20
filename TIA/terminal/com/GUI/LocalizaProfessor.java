package com.GUI;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.tabelas.tableModel.LocalizacaoTableModel;
import com.tia.controller.ControladorDocente;
import com.tia.controller.ControladorLocalizacao;
import com.tia.model.Professor;
import com.tia.view.models.table.ProfessorTableModel;

public class LocalizaProfessor extends JInternalFrame {
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
	private JTable tabGeral;
	private ProfessorTableModel tableModelGeral;


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
	setVisible(true);
	setBounds(100, 100, 800, 600);
	getContentPane().setLayout(null);
	
	JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	tabbedPane.setBounds(0, 0, 784, 571);
	getContentPane().add(tabbedPane);
	
	JLabel lblMensagemParametrizada = new JLabel("Mensagem Parametrizada");
	tabbedPane.addTab("Instruções", null, lblMensagemParametrizada, null);
	geraTabGeral();
	tabbedPane.addTab("Todos os cursos", null, tabGeral, null);

    }

    /**
     * Gera a tabela geral de localizações
     * @author bruno.martins
     * @since 08/05/2014  
     */
    private void geraTabGeral( ) {
	if(tabGeral == null) {
	    tabGeral = new JTable();
	    tabGeral.setModel(geraModelTabGeral());
	}
    }

    private TableModel geraModelTabGeral() {
	if(tableModelGeral == null) {
//	    ControladorLocalizacao crtl = new ControladorLocalizacao();
//	    tableModelGeral = new LocalizacaoTableModel(crtl.getLocalizacao());
	    ControladorDocente crtl = new ControladorDocente();	
	    List<Object> professores = Arrays.asList(crtl.ler());
	    tableModelGeral = new ProfessorTableModel(professores);
	}
	return tableModelGeral;
    }
}
