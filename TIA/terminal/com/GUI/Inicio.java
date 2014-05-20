package com.GUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.mensagens.Messages;

public class Inicio {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    Inicio window = new Inicio();
		    window.frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public Inicio() {
	initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
	frame = new JFrame();
	frame.setResizable(false);
	frame.setBounds(100, 100, 1440, 900);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	GraphicsDevice myDevice = GraphicsEnvironment
		.getLocalGraphicsEnvironment().getScreenDevices()[0];

	try {
	    myDevice.setFullScreenWindow(frame);
	    frame.getContentPane().setLayout(null);
	    
	    JLabel lblMensagemAbertura = new JLabel(Messages.getString("Inicio.lblMensagemAbertura.text"));
	    lblMensagemAbertura.setHorizontalAlignment(SwingConstants.CENTER);
	    lblMensagemAbertura.setBounds(19, 88, 1405, 165);
	    lblMensagemAbertura.setOpaque(true);
	    lblMensagemAbertura.setForeground(new Color(0, 0, 0));
	    lblMensagemAbertura.setFont(new Font("Tahoma", Font.PLAIN, 27));
	    frame.getContentPane().add(lblMensagemAbertura);
	    
	    JButton btnComear = new JButton(Messages.getString("Inicio.btnComear.text")); //$NON-NLS-1$
	    btnComear.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	    Menu.main(null);
	    	}
	    });
	    btnComear.setBounds(226, 597, 1031, 149);
	    btnComear.setFont(new Font("Tahoma", Font.PLAIN, 34));
	    frame.getContentPane().add(btnComear);
	} finally {
	    myDevice.setFullScreenWindow(frame);
	}
    }
}
