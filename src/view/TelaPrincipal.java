package view;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	public static JMenuItem menCadUsu;
	public static JMenuItem menRelSer;
	public static JLabel lblUsuario;
	private JDesktopPane desktop ;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		
		setResizable(false);
		setTitle("X - Sistema de controle de ordem de serviço");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 985, 615);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menu = new JMenuBar();
		menu.setBounds(0, 0, 969, 22);
		contentPane.add(menu);
		
		JMenu menCad = new JMenu("Cadastro");
		menu.add(menCad);
		
		JMenuItem menCadCli = new JMenuItem("Cliente");
		menCadCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCliente cliente = new TelaCliente();
				cliente.setVisible(true);

	    		desktop.add(cliente);
	    		cliente.setLocation(0, 0); 
			}
		});
		menCadCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_DOWN_MASK));
		menCad.add(menCadCli);
		
		JMenuItem menCadOs = new JMenuItem("OS");
		menCadOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaOS os = new TelaOS();
				os.setVisible(true);
				desktop.add(os);
				os.setLocation(0, 0);
			}
		});
		menCadOs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_DOWN_MASK));
		menCad.add(menCadOs);
		
	    menCadUsu = new JMenuItem("Usuários");
	    menCadUsu.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		TelaUsuario usuario = new TelaUsuario();
	    		usuario.setVisible(true);
	    		 // Maximiza a janela
	         
	            
	    		desktop.add(usuario);
	    		usuario.setLocation(0, 0); 
	    	}
	    });
		menCadUsu.setEnabled(false);
		menCadUsu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_DOWN_MASK));
		menCad.add(menCadUsu);
		
	    JMenu menRel = new JMenu("Relatório");
		menu.add(menRel);
		
	   menRelSer = new JMenuItem("Serviços");
		menRelSer.setEnabled(false);
		menRelSer.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_DOWN_MASK));
		menRel.add(menRelSer);
		
		JMenu menAjuda = new JMenu("Ajuda");
		menu.add(menAjuda);
		
		JMenuItem menAjudaSob = new JMenuItem("Sobre");
		menAjudaSob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaSobre sobre  = new TelaSobre();
				sobre.setLocationRelativeTo(null);
				sobre.setVisible(true);
				
			}
		});
		menAjudaSob.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.CTRL_DOWN_MASK));
		menAjuda.add(menAjudaSob);
		
		JMenu menOpcao = new JMenu("Opções");
		menu.add(menOpcao);
		
		JMenuItem menOpcaoSair = new JMenuItem("Sair");
		menOpcaoSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int sair  = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
				if(sair == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
		menOpcaoSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
		menOpcao.add(menOpcaoSair);
		
		desktop = new JDesktopPane();
		desktop.setBackground(Color.DARK_GRAY);
		desktop.setBounds(0, 22, 797, 554);
		contentPane.add(desktop);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/icones/x.png")));
		lblNewLabel.setBounds(815, 416, 154, 160);
		contentPane.add(lblNewLabel);
		
	    lblUsuario = new JLabel("Usuário");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblUsuario.setBounds(805, 114, 154, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblData = new JLabel("Data");
		lblData.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblData.setBounds(805, 217, 154, 22);
		contentPane.add(lblData);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				Date data = new Date();
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);
				lblData.setText(formatador.format(data));
				
			}
		});
	}
}
