package com.tia.toten.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Classe responsável pelo menu do TIA - Terminal
 * @author Bruno
 * @since 25/05/2014
 * @version 25/05/2014
 *
 */
public class Menu extends JFrame {


	private static final long serialVersionUID = -7636632531162416414L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 759, 451);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOndeEstaMeu = new JButton("Onde esta meu professor?");
		btnOndeEstaMeu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalizaProfessor localiza = new LocalizaProfessor();
				localiza.setVisible(true);
			}
		});
		btnOndeEstaMeu.setFont(new Font("SansSerif", Font.PLAIN, 17));
		btnOndeEstaMeu.setBounds(10, 20, 246, 43);
		contentPane.add(btnOndeEstaMeu);
	}
}
