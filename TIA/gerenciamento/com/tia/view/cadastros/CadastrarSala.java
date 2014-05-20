package com.tia.view.cadastros;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.tia.controller.CadastrarSalaController;
import com.tia.controller.constantes.Persistencia;

public class CadastrarSala extends JInternalFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 1L; 
    private JPanel contentPane;
    private JTextField tfNome;

    public CadastrarSala() {
    	setClosable(true);
    	setIconifiable(true);
    	setTitle("Cadastrar Sala");
    	setResizable(false);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 362, 212);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(null);
	
	JPanel panel = new JPanel();
	panel.setBorder(new TitledBorder(new LineBorder(new Color(171, 173, 179)), "Dados da Sala", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
	panel.setBounds(10, 6, 338, 123);
	contentPane.add(panel);
	panel.setLayout(new FormLayout(new ColumnSpec[] {
			FormFactory.UNRELATED_GAP_COLSPEC,
			ColumnSpec.decode("128px"),
			FormFactory.UNRELATED_GAP_COLSPEC,
			ColumnSpec.decode("118px"),
			FormFactory.LABEL_COMPONENT_GAP_COLSPEC,
			ColumnSpec.decode("132px"),},
		new RowSpec[] {
			FormFactory.RELATED_GAP_ROWSPEC,
			RowSpec.decode("25px"),
			RowSpec.decode("23px"),
			FormFactory.RELATED_GAP_ROWSPEC,
			RowSpec.decode("max(11dlu;default)"),}));
	
	JLabel lblNmeroDaSala = new JLabel("Nome da sala");
	lblNmeroDaSala.setFont(new Font("Tahoma", Font.PLAIN, 17));
	panel.add(lblNmeroDaSala, "2, 2, fill, top");
	
	tfNome = new JTextField();
	panel.add(tfNome, "4, 2, fill, fill");
	tfNome.setColumns(10);
	
	JLabel lblPossuiEquipamentosEletrnicas = new JLabel("Possui equipamentos eletrônicos?");
	lblPossuiEquipamentosEletrnicas.setFont(new Font("Tahoma", Font.PLAIN, 17));
	panel.add(lblPossuiEquipamentosEletrnicas, "2, 3, 3, 1, fill, fill");
	
	final JCheckBox checkEquipamentos = new JCheckBox("Sim");
	checkEquipamentos.setFont(new Font("Tahoma", Font.PLAIN, 17));
	panel.add(checkEquipamentos, "6, 3, left, bottom");
	
	JLabel lblAndar = new JLabel("Andar");
	lblAndar.setFont(new Font("Tahoma", Font.PLAIN, 17));
	panel.add(lblAndar, "2, 5, left, default");
	
	final JSpinner spinAndar = new JSpinner();
	spinAndar.setModel(new SpinnerNumberModel(new Short((short) 0), new Short((short) 0), new Short((short) 2), new Short((short) 1)));
	spinAndar.setFont(new Font("Tahoma", Font.PLAIN, 17));
	panel.add(spinAndar, "4, 5, left, default");
	
	JButton btnGravar = new JButton("Gravar");
	btnGravar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    
		    //FIXME HashTable
		    Hashtable<String, Object> parametros = new Hashtable<>();
		   
			parametros.put("nome", tfNome.getText());
			parametros.put("andar", spinAndar.getValue());
			parametros.put("equipamento", checkEquipamentos.isSelected());
			CadastrarSalaController crtl = new CadastrarSalaController();
			Persistencia response = null;
			if (crtl.validaEntradas(parametros))
			    response = crtl.persistir(parametros);
			crtl.validaPersistencia(response);			
			crtl = null;
		    
		}
	});
	btnGravar.setFont(new Font("Tahoma", Font.PLAIN, 17));
	btnGravar.setBounds(128, 141, 106, 30);
	contentPane.add(btnGravar);
	
	JButton btnCancelar = new JButton("Cancelar");
	btnCancelar.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		    dispose();
		}
	});
	btnCancelar.setFont(new Font("Tahoma", Font.PLAIN, 17));
	btnCancelar.setBounds(246, 141, 98, 30);
	contentPane.add(btnCancelar);
    }
    
    
    
    
}
