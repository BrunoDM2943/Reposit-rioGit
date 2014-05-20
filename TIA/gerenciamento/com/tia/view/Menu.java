package com.tia.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

import com.framework.SistemaArquivos;
import com.tia.view.cadastros.CadastrarAulaWindow;
import com.tia.view.cadastros.CadastrarCurso;
import com.tia.view.cadastros.CadastrarDisciplinaWindow;
import com.tia.view.cadastros.CadastrarProfessor;
import com.tia.view.cadastros.CadastrarSala;
import com.tia.view.cadastros.CadastroStatusWindow;
import com.tia.view.gerenciadores.TabelaCursoWindow;
import com.tia.view.gerenciadores.TabelaDisciplinaWindow;
import com.tia.view.gerenciadores.TabelaDocenteWindow;
import com.tia.view.gerenciadores.TabelaProfessorWindow;
import com.tia.view.gerenciadores.TabelaSalaWindow;

public class Menu {

    private JFrame frmMenu;
    public static JDesktopPane desktopPane = new JDesktopPane();
    public String a;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {

	    public void run() {
		try {
		    Menu window = new Menu();
		    window.frmMenu.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public Menu() {
	SistemaArquivos.checarDiretorios();
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	try {
	    UIManager.setLookAndFeel(new NimbusLookAndFeel());
	} catch (UnsupportedLookAndFeelException ex) {
	    ex.printStackTrace();
	}
	
	frmMenu = new JFrame();
	frmMenu.setTitle("Menu");
	frmMenu.setBounds(100, 100, 854, 582);
	frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JMenuBar menuBar = new JMenuBar();
	frmMenu.setJMenuBar(menuBar);

	JMenu mnNewMenu = new JMenu("Arquivo");
	menuBar.add(mnNewMenu);

	JMenu mnCadastrar = new JMenu("Cadastrar");
	menuBar.add(mnCadastrar);

	JMenuItem mntmProfessor = new JMenuItem("Professor");
	mntmProfessor.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		CadastrarProfessor frame = new CadastrarProfessor();
		frame.setVisible(true);
		desktopPane.add(frame);

	    }
	});
	
	JMenuItem mntmAula = new JMenuItem("Aula");
	mntmAula.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			CadastrarAulaWindow aula = new CadastrarAulaWindow();
			aula.setVisible(true);
			desktopPane.add(aula);
		}
	});
	mnCadastrar.add(mntmAula);
	mnCadastrar.add(mntmProfessor);

	JMenuItem mntmCurso = new JMenuItem("Curso");
	mntmCurso.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		CadastrarCurso curso = new CadastrarCurso();
		curso.setVisible(true);
		desktopPane.add(curso);
	    }
	});
	mnCadastrar.add(mntmCurso);

	JMenuItem mntmSala = new JMenuItem("Sala");
	mntmSala.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		CadastrarSala sala = new CadastrarSala();
		sala.setVisible(true);
		desktopPane.add(sala);
	    }
	});
	
	JMenuItem mntmDisciplina = new JMenuItem("Disciplina");
	mntmDisciplina.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			CadastrarDisciplinaWindow disciplinaWindow = new CadastrarDisciplinaWindow();
		    disciplinaWindow.setVisible(true);
		    desktopPane.add(disciplinaWindow);
		}
	});
	mnCadastrar.add(mntmDisciplina);
	mnCadastrar.add(mntmSala);
	
	JMenuItem mntmStatus = new JMenuItem("Status");
	mntmStatus.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			CadastroStatusWindow statusWindow = new CadastroStatusWindow();
			statusWindow.setVisible(true);
		}
	});
	mnCadastrar.add(mntmStatus);

	JMenu mnGerencimaneto = new JMenu("Gerenciamento");
	menuBar.add(mnGerencimaneto);

	JMenuItem mntmControleDeLocalizacao = new JMenuItem(
		"Controle de Localizacao");
	mntmControleDeLocalizacao.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) {
		

	    }
	});
	mnGerencimaneto.add(mntmControleDeLocalizacao);

	JMenu mnTabelas = new JMenu("Tabelas");
	menuBar.add(mnTabelas);
	
	JMenuItem mntmCursos = new JMenuItem("Cursos");
	mntmCursos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    TabelaCursoWindow tabela = new TabelaCursoWindow();
		    tabela.setVisible(true);
		    desktopPane.add(tabela);
		}
	});
	mnTabelas.add(mntmCursos);

	JMenuItem mntmProfessores = new JMenuItem("Professores");
	mntmProfessores.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    TabelaProfessorWindow tabelaProfessorWindow = new TabelaProfessorWindow();
		    tabelaProfessorWindow.setVisible(true);
		    desktopPane.add(tabelaProfessorWindow);
		}
	});
	
	JMenuItem mntmDisciplina_1 = new JMenuItem("Disciplina");
	mntmDisciplina_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			TabelaDisciplinaWindow tableDisc = new TabelaDisciplinaWindow();
			tableDisc.setVisible(true);
			desktopPane.add(tableDisc);
		}
	});
	mnTabelas.add(mntmDisciplina_1);
	
	JMenuItem mntmDocentes = new JMenuItem("Docentes");
	mntmDocentes.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			TabelaDocenteWindow docente = new TabelaDocenteWindow();
			docente.setVisible(true);
			desktopPane.add(docente);
		}
	});
	mnTabelas.add(mntmDocentes);
	mnTabelas.add(mntmProfessores);
	
	JMenuItem mntmSala_1 = new JMenuItem("Sala");
	mntmSala_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    TabelaSalaWindow window = new TabelaSalaWindow();
		    window.setVisible(true);
		    desktopPane.add(window);
		}
	});
	mnTabelas.add(mntmSala_1);
	frmMenu.getContentPane().setLayout(new BorderLayout(0, 0));
	
	
	frmMenu.getContentPane().add(desktopPane, BorderLayout.CENTER);
	desktopPane.setBackground(UIManager.getColor("Menu.background"));
    }
}
