package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class TelaSobre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public TelaSobre() {
		setResizable(false);
		setTitle("Sobre");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sistema para controle de Ordem de  Serviços");
		lblNewLabel.setBounds(10, 35, 259, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sobre a licença GPL");
		lblNewLabel_1.setBounds(10, 83, 151, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Desenvolvido por  Luiz Felipe");
		lblNewLabel_1_1.setBounds(10, 58, 181, 14);
		contentPane.add(lblNewLabel_1_1);
	}
}
