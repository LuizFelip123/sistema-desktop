package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import conexao.Conexao;

import java.awt.BorderLayout;
import java.awt.Cursor;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;

public class TelaUsuario extends JInternalFrame {
	private JTextField txtUseId;
	private JTextField txtUsuNome;
	private JTextField txtUsuLogin;
	private JTextField txtUsuFone;
	private JTextField txtUsuSenha;
	private Connection connection;
	private ResultSet result;
	private PreparedStatement statement;
	private JComboBox comboUsuPerfil;

	 /**
	 * Create the frame.
	 */
	public TelaUsuario() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Usuário");
		setBounds(100, 576, 797, 554);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("* ID");
		lblNewLabel.setBounds(10, 68, 46, 14);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("* Nome");
		lblNewLabel_1.setBounds(10, 127, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("* Login");
		lblNewLabel_2.setBounds(10, 184, 46, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Fone");
		lblNewLabel_3.setBounds(336, 257, 35, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("* Perfil");
		lblNewLabel_4.setBounds(10, 257, 46, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtUseId = new JTextField();
		txtUseId.setBounds(56, 65, 163, 20);
		getContentPane().add(txtUseId);
		txtUseId.setColumns(10);
		
		txtUsuNome = new JTextField();
		txtUsuNome.setBounds(56, 124, 643, 20);
		getContentPane().add(txtUsuNome);
		txtUsuNome.setColumns(10);
		
		txtUsuLogin = new JTextField();
		txtUsuLogin.setBounds(56, 181, 259, 20);
		getContentPane().add(txtUsuLogin);
		txtUsuLogin.setColumns(10);
		
		txtUsuFone = new JTextField();
		txtUsuFone.setBounds(370, 254, 329, 20);
		getContentPane().add(txtUsuFone);
		txtUsuFone.setColumns(10);
		
	 comboUsuPerfil = new JComboBox();
		comboUsuPerfil.setModel(new DefaultComboBoxModel(new String[] {"admin", "user"}));
		comboUsuPerfil.setBounds(56, 253, 257, 22);
		getContentPane().add(comboUsuPerfil);
		
		JLabel lblNewLabel_3_1 = new JLabel("* Senha");
		lblNewLabel_3_1.setBounds(353, 184, 55, 14);
		getContentPane().add(lblNewLabel_3_1);
		
		txtUsuSenha = new JTextField();
		txtUsuSenha.setColumns(10);
		txtUsuSenha.setBounds(415, 181, 281, 20);
		getContentPane().add(txtUsuSenha);
		
		JButton btnUsuAdicionar = new JButton("");
		btnUsuAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}

				});
		btnUsuAdicionar.setToolTipText("Adicionar");
		btnUsuAdicionar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/create.png")));
		btnUsuAdicionar.setBounds(71, 344, 80, 80);
	     // Cursor padrão
        Cursor defaultCursor = Cursor.getDefaultCursor();

        // Cursor personalizado (exemplo: mãozinha)
        Cursor customCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        btnUsuAdicionar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnUsuAdicionar.setCursor(customCursor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnUsuAdicionar.setCursor(defaultCursor);
            }
        });
		getContentPane().add(btnUsuAdicionar);
		
		JButton btnUsuBuscar = new JButton("");
		btnUsuBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consulta();
			}
		});
		btnUsuBuscar.setToolTipText("Pesquisar");
		btnUsuBuscar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/read.png")));
		btnUsuBuscar.setBounds(230, 344, 80, 80);
		btnUsuBuscar.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseEntered(MouseEvent e) {
	            	btnUsuBuscar.setCursor(customCursor);
	            }

	            @Override
	            public void mouseExited(MouseEvent e) {
	            	btnUsuBuscar.setCursor(defaultCursor);
	            }
	        });

		getContentPane().add(btnUsuBuscar);
		
		JButton btnUsuEditar = new JButton("");
		btnUsuEditar.setToolTipText("Editar");
		btnUsuEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnUsuEditar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/update.png")));
		btnUsuEditar.setBounds(386, 344, 80, 80);
		getContentPane().add(btnUsuEditar);
		btnUsuEditar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnUsuBuscar.setCursor(customCursor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnUsuBuscar.setCursor(defaultCursor);
            }
        });
		JButton btnUsuDeletar = new JButton("");
		btnUsuDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnUsuDeletar.setToolTipText("Deletar");
		btnUsuDeletar.setIcon(new ImageIcon(TelaUsuario.class.getResource("/icones/delete.png")));
		btnUsuDeletar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            	btnUsuBuscar.setCursor(customCursor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
            	btnUsuBuscar.setCursor(defaultCursor);
            }
        });
		btnUsuDeletar.setBounds(543, 344, 80, 80);
		getContentPane().add(btnUsuDeletar);
		
		JLabel lblNewLabel_5 = new JLabel("*Campos Obrigátorios");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(560, 68, 185, 20);
		getContentPane().add(lblNewLabel_5);

	}
	private void consulta() {
		String sql = "select * from tbusuarios where iduser = ?";
		
		try {
			connection	= Conexao.conection();
			statement	= connection.prepareStatement(sql);
			statement.setString(1, txtUseId.getText());
			result = statement.executeQuery();
			if (result.next() == true) {
				txtUsuNome.setText(result.getString(2));
				txtUsuFone.setText(result.getString(3));
				txtUsuLogin.setText(result.getString(4));
				txtUsuSenha.setText(result.getString(5));
				comboUsuPerfil.setSelectedItem(result.getString(6));
			} else {
				JOptionPane.showMessageDialog(null, "Usuário não encontrado!!");
				txtUsuNome.setText(null);
				txtUsuFone.setText(null);
				txtUsuLogin.setText(null);
				txtUsuSenha.setText(null);
    			}
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 		}
	}
	private void adicionar() {
		String sql ="insert into tbusuarios (iduser, usuario, fone, login, senha, perfil) values(?, ?, ? ,? ,?, ?) ";
		try {
			connection = Conexao.conection();
			 statement = connection.prepareStatement(sql);
			 statement.setString(1,txtUseId.getText());
			 statement.setString(2,txtUsuNome.getText());
			 statement.setString(3, txtUsuFone.getText());
			 statement.setString(4, txtUsuLogin.getText());
			 statement.setString(5, txtUsuSenha.getText());
			 statement.setString(6, comboUsuPerfil.getSelectedItem().toString());
			 if(txtUseId.getText().isEmpty() || txtUsuNome.getText().isEmpty() || txtUsuLogin.getText().isEmpty() || txtUsuSenha.getText().isEmpty()) {
				 JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				 return;
			 }
			 int adicionado = statement.executeUpdate();
		     if( adicionado >0) {
		    	 JOptionPane.showMessageDialog(null, "Dados Salvos!");	
		    	 	txtUseId.setText(null);
		    		txtUsuNome.setText(null);
					txtUsuFone.setText(null);
					txtUsuLogin.setText(null);
					txtUsuSenha.setText(null);

		     }else {
		    	 JOptionPane.showMessageDialog(null, "Dados não salvos!!", "Erro", JOptionPane.ERROR_MESSAGE);
		     }
		} catch (Exception e) {
			// TODO: handle exception
		 	 JOptionPane.showMessageDialog(null,e , "Erro", JOptionPane.ERROR_MESSAGE);
			  
		}
	}
	private void delete() {
		String sql = "delete from tbusuarios where iduser = ?";
		int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuário?","Deletar", JOptionPane.YES_NO_OPTION);
		if(opcao == JOptionPane.YES_OPTION) {
			try {
				connection	= Conexao.conection();
				statement	= connection.prepareStatement(sql);
				statement.setInt(1,  Integer.parseInt(txtUseId.getText()));
				int exec = statement.executeUpdate();
				if ( exec == 1) {
					JOptionPane.showMessageDialog(null, "Usuário deletado!!");
					txtUseId.setText(null);
					txtUsuNome.setText(null);
					txtUsuFone.setText(null);
					txtUsuLogin.setText(null);
					txtUsuSenha.setText(null);
				} else {
					JOptionPane.showMessageDialog(null, "Usuário não deletado!!", "Erro!", JOptionPane.ERROR_MESSAGE);

				}
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
	private void update() {
		String sql ="update tbusuarios set  usuario = ?, fone= ?, login = ?, senha =  ?, perfil = ? where iduser =?";
		try {
			connection = Conexao.conection();
			 statement = connection.prepareStatement(sql);
			
			 statement.setString(1,txtUsuNome.getText());
			 statement.setString(2, txtUsuFone.getText());
			 statement.setString(3, txtUsuLogin.getText());
			 statement.setString(4, txtUsuSenha.getText());
			 statement.setString(5, comboUsuPerfil.getSelectedItem().toString());
			 statement.setString(6,txtUseId.getText());
			 if(txtUseId.getText().isEmpty() || txtUsuNome.getText().isEmpty() || txtUsuLogin.getText().isEmpty() || txtUsuSenha.getText().isEmpty()) {
				 JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				 return;
			 }
			 int adicionado = statement.executeUpdate();
		     if( adicionado >0) {
		    	 JOptionPane.showMessageDialog(null, "Dados Salvos!");	
		    	 	txtUseId.setText(null);
		    		txtUsuNome.setText(null);
					txtUsuFone.setText(null);
					txtUsuLogin.setText(null);
					txtUsuSenha.setText(null);

		     }else {
		    	 JOptionPane.showMessageDialog(null, "Dados não salvos!!", "Erro", JOptionPane.ERROR_MESSAGE);
		     }
		} catch (Exception e) {
			// TODO: handle exception
		 	 JOptionPane.showMessageDialog(null,e , "Erro", JOptionPane.ERROR_MESSAGE);
			  
		}
	}

}
