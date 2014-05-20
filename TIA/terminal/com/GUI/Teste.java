package com.GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Teste extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private final JButton btnNewButton = new JButton("Teste");

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Teste frame = new Teste();
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
    public Teste() {
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 872, 427);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    LocalizaProfessor localiza = new LocalizaProfessor(); 
		    getContentPane().add(localiza); 
		    localiza.show(); 
		     
		}
	});
	btnNewButton.setBounds(5, 361, 846, 23);
	contentPane.add(btnNewButton);
    }

}
