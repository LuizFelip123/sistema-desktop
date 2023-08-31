package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import conexao.Conexao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField pwdSenha;
	private JLabel lblConexao;
	private Connection connection;
	private ResultSet result;
	private PreparedStatement statement;
	private final Action action = new SwingAction();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
				
					frame.setLocationRelativeTo(null);
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
	public Login() {
		setTitle("X System - Info");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblConexao = new JLabel("");
		connection = Conexao.conection();
		if(connection == null) {
			lblConexao.setIcon(new ImageIcon(Login.class.getResource("/icones/dberror.png")));

		}else {
			lblConexao.setIcon(new ImageIcon(Login.class.getResource("/icones/dbok.png")));

		}

		lblConexao.setBounds(37, 209, 79, 41);
		contentPane.add(lblConexao);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(37, 89, 330, 27);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Senha");
		lblNewLabel_1.setBounds(37, 127, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Usuário");
		lblNewLabel_1_1.setBounds(33, 64, 46, 14);
		contentPane.add(lblNewLabel_1_1);
		
		pwdSenha = new JPasswordField();
		pwdSenha.setBounds(37, 152, 330, 27);
		contentPane.add(pwdSenha);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Clicou");
				logar();

			}
		});
		
	
		btnLogin.setBounds(278, 200, 89, 23);
		contentPane.add(btnLogin);
	}
	public void logar() {
		String sql = "select * from tbusuarios  where login =? and senha =?";
		try {
			statement = connection.prepareStatement(sql);
			statement.setString(1, txtUsuario.getText());
			String captura = new String(pwdSenha.getPassword());
			statement.setString(2, captura);
			result = statement.executeQuery();
			if(result.next() == true) {
				String perfil  = result.getString(6);
				System.out.println(perfil);
				if(perfil.equals("admin")) {
					TelaPrincipal telaPrincipal = new TelaPrincipal();
					telaPrincipal.setLocationRelativeTo(null);
					telaPrincipal.setVisible(true);
					TelaPrincipal.menCadUsu.setEnabled(true);
					TelaPrincipal.menRelSer.setEnabled(true);
					TelaPrincipal.lblUsuario.setText(result.getString(2));
					TelaPrincipal.lblUsuario.setForeground(Color.red);
					this.dispose();
				}else {
					TelaPrincipal telaPrincipal = new TelaPrincipal();
					telaPrincipal.setLocationRelativeTo(null);
					telaPrincipal.setVisible(true);
					TelaPrincipal.lblUsuario.setText(result.getString(2));
					
					this.dispose();
				}
			
				connection.close();
			}else {
				System.out.println("Usuário não encontrado!");
				JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválido!");
				
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	}
