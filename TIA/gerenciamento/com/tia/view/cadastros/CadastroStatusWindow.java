package com.tia.view.cadastros;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.tia.controller.cadastro.CadastrarStatusController;
import com.tia.controller.constantes.Persistencia;

public class CadastroStatusWindow extends JDialog {


	private static final long serialVersionUID = 7569711051282358855L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tfStatus;
	

	public CadastroStatusWindow() {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 126);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		{
			JLabel lblDigiteUmNovo = new JLabel("Digite um novo status para localização dos professores");
			contentPanel.add(lblDigiteUmNovo, "2, 2");
		}
		{
			tfStatus = new JTextField();
			contentPanel.add(tfStatus, "2, 4, fill, default");
			tfStatus.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Gravar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						CadastrarStatusController crtl = new CadastrarStatusController();
						Persistencia response = null;
						Hashtable<String, Object> parametros = new Hashtable<String, Object>();
						parametros.put("status", tfStatus.getText());
						if(crtl.validaEntradas(parametros))
							response = crtl.persistir(parametros);
						crtl.validaPersistencia(response);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
