package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;

import conexao.Conexao;
import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class TelaCliente extends JInternalFrame {
	private JTextField txtCliNome;
	private JTextField txtCliTelefone;
	private JTextField txtCliEndereco;
	private JTextField txtCliEmail;
	private JTextField txtCliBusca;
	private JTable table;
	private Connection connection;
	private ResultSet result;
	private PreparedStatement statement;
	private JTextField txtCliID;
	private JButton btnCliAdicionar;
	/**
	 * @wbp.nonvisual location=-30,339
	 */
	private final JPanel panel = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setTitle("Clientes");
		setBounds(100, 576, 797, 554);
		getContentPane().setLayout(null);
		
		btnCliAdicionar = new JButton("");
		btnCliAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnCliAdicionar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/create.png")));
		btnCliAdicionar.setToolTipText("Adicionar");
		btnCliAdicionar.setBounds(193, 407, 80, 80);
		getContentPane().add(btnCliAdicionar);
		
		JButton btnCliEditar = new JButton("");
		btnCliEditar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/update.png")));
		btnCliEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnCliEditar.setToolTipText("Adicionar");
		btnCliEditar.setBounds(362, 407, 80, 80);
		getContentPane().add(btnCliEditar);
		
		JButton btnCliDeletar = new JButton("");
		btnCliDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnCliDeletar.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/delete.png")));
		btnCliDeletar.setToolTipText("Adicionar");
		btnCliDeletar.setBounds(525, 407, 80, 80);
		getContentPane().add(btnCliDeletar);
		
		JLabel lblNewLabel_5 = new JLabel("*Campos Obrigátorios");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(586, 64, 185, 20);
		getContentPane().add(lblNewLabel_5);
		
		txtCliNome = new JTextField();
		txtCliNome.setColumns(10);
		txtCliNome.setBounds(109, 264, 422, 20);
		getContentPane().add(txtCliNome);
		
		JLabel lblNewLabel_1 = new JLabel("* Nome");
		lblNewLabel_1.setBounds(42, 267, 46, 14);
		getContentPane().add(lblNewLabel_1);
		
		txtCliTelefone = new JTextField();
		txtCliTelefone.setColumns(10);
		txtCliTelefone.setBounds(109, 349, 422, 20);
		getContentPane().add(txtCliTelefone);
		
		JLabel lblNewLabel_3 = new JLabel("Email");
		lblNewLabel_3.setBounds(57, 390, 35, 14);
		getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel("* Endereço");
		lblNewLabel_1_1.setBounds(42, 311, 70, 14);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("* Telefone");
		lblNewLabel_1_1_1.setBounds(42, 352, 71, 14);
		getContentPane().add(lblNewLabel_1_1_1);
		
		txtCliEndereco = new JTextField();
		txtCliEndereco.setColumns(10);
		txtCliEndereco.setBounds(109, 308, 496, 20);
		getContentPane().add(txtCliEndereco);
		
		txtCliEmail = new JTextField();
		txtCliEmail.setColumns(10);
		txtCliEmail.setBounds(109, 387, 496, 20);
		getContentPane().add(txtCliEmail);
		
		txtCliBusca = new JTextField();
		txtCliBusca.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisaCliente();
			}
		});
		txtCliBusca.setColumns(10);
		txtCliBusca.setBounds(29, 30, 301, 20);
		getContentPane().add(txtCliBusca);
		
		JLabel lblCliBusca = new JLabel("");
		lblCliBusca.setIcon(new ImageIcon(TelaCliente.class.getResource("/icones/pesquisar.png")));
		lblCliBusca.setBounds(348, 18, 46, 32);
		getContentPane().add(lblCliBusca);
		
		JScrollPane tblClientes = new JScrollPane();
		tblClientes.setBounds(10, 68, 566, 145);
		getContentPane().add(tblClientes);
		
		table = new JTable();
		tblClientes.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Email", "Endere\u00E7o", "Telefone", "Endereco"
			}
			
		) {
			@Override
		    public boolean isCellEditable(int row, int column) {
		        return column == 1; // Tornar apenas a coluna "Nome" editável
		    }

		}
);
		table.getTableHeader().setReorderingAllowed (false);
		
		

		table.setColumnSelectionAllowed(true);
		

		// Configurando a seleção de linhas
		table.setColumnSelectionAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Ou MULTIPLE_SELECTION, se necessário

		// Adicionando um ouvinte de seleção de linhas
		table.getSelectionModel().addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		        int selectedRow = table.getSelectedRow();
		        if (selectedRow != -1) {
		            // Aqui você pode obter os dados da linha selecionada
		    		txtCliID.setText(table.getModel().getValueAt(selectedRow, 0).toString());
		    		txtCliNome.setText(table.getModel().getValueAt(selectedRow, 1).toString());
		    		txtCliEndereco.setText(table.getModel().getValueAt(selectedRow,2 ).toString());
		    		txtCliTelefone.setText(table.getModel().getValueAt(selectedRow, 3).toString());
		    		txtCliEmail.setText(table.getModel().getValueAt(selectedRow, 4).toString());
		    		btnCliAdicionar.setEnabled(false);
		            
		        }
		    }
		});

		JLabel lblNewLabel_1_2 = new JLabel("ID do Cliente");
		lblNewLabel_1_2.setBounds(42, 235, 80, 14);
		getContentPane().add(lblNewLabel_1_2);
		
		txtCliID = new JTextField();
		txtCliID.setEnabled(false);
		txtCliID.setBounds(132, 232, 98, 20);
		getContentPane().add(txtCliID);
		txtCliID.setColumns(10);

	}
	private void adicionar() {
		String sql = "insert into tbclientes(nomecli, endcli, fonecli, email) values(?,?,?,?)";
		try {
			connection = Conexao.conection();
		  statement	=connection.prepareStatement(sql);
		  statement.setString(1,txtCliNome.getText());
		  statement.setString(2,txtCliEndereco.getText());
		  statement.setString(3,txtCliTelefone.getText());
		  statement.setString(4,txtCliEmail.getText());
		  if(txtCliNome.getText().isEmpty() || txtCliEndereco.getText().isEmpty() || txtCliTelefone.getText().isEmpty()) {
			  JOptionPane.showMessageDialog(null,"Preencha todos os campos!");
			  return;
		  }
		int insert =  statement.executeUpdate();
		if(insert > 0) {
			JOptionPane.showMessageDialog(null, "Dados Salvos!!");	
			limpar();
		}else {
			JOptionPane.showMessageDialog(null, "Problema ao salvar!!");	
			
		}
		
		
		}catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, e);	
			
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void consulta() {
		
	}
	private void pesquisaCliente() {
		String  sql  = "select idcli as ID, nomecli as Nome, email as Email, fonecli as Telefone,  endcli as Endereço  from tbclientes  where nomecli like ?";
		try {
			connection = Conexao.conection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, txtCliBusca.getText()+"%");
			result = statement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(result));
			
		} catch (Exception e) {
			// TODO: handle exception
		JOptionPane.showMessageDialog(null, e);
		}
	}
	private void update() {
		String sql ="update tbclientes set  nomecli = ?,  endcli = ?, fonecli = ?, email =? where idcli = ?";
		try {
			connection = Conexao.conection();
			 statement = connection.prepareStatement(sql);
			
			  statement.setString(1,txtCliNome.getText());
			  statement.setString(2,txtCliEndereco.getText());
			  statement.setString(3,txtCliTelefone.getText());
			  statement.setString(4,txtCliEmail.getText());
			  statement.setString(5,txtCliID.getText());
			  if( txtCliNome.getText().isEmpty() || txtCliEndereco.getText().isEmpty()|| txtCliTelefone.getText().isEmpty()) {
				 JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				 return;
			 }
			 int adicionado = statement.executeUpdate();
		     if( adicionado > 0) {
		    	 JOptionPane.showMessageDialog(null, "Dados Salvos!");	
		 
		    	 limpar();
		    	 btnCliAdicionar.setEnabled(true);
			
		     }else {
		    	 JOptionPane.showMessageDialog(null, "Dados não salvos!!", "Erro", JOptionPane.ERROR_MESSAGE);
		     }
		} catch (Exception e) {
			// TODO: handle exception
		 	 JOptionPane.showMessageDialog(null,e , "Erro", JOptionPane.ERROR_MESSAGE);
			  
		}
	}
	private void delete() {
		String sql = "delete from tbclientes where idcli = ?";
		int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover o usuário?","Deletar", JOptionPane.YES_NO_OPTION);
		if(opcao == JOptionPane.YES_OPTION) {
			try {
				connection	= Conexao.conection();
				statement	= connection.prepareStatement(sql);
				statement.setInt(1,  Integer.parseInt(txtCliID.getText()));
				int exec = statement.executeUpdate();
				if ( exec == 1) {
					JOptionPane.showMessageDialog(null, "Cliente deletado!!");
					limpar();
					btnCliAdicionar.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "Cliente não deletado!!", "Erro!", JOptionPane.ERROR_MESSAGE);

				}
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
private void limpar() {
	txtCliBusca.setText(null);
	txtCliEmail.setText(null);
	txtCliNome.setText(null);
	txtCliTelefone.setText(null);
	txtCliEndereco.setText(null);
	txtCliID.setText(null);
	((DefaultTableModel)table.getModel()).setRowCount(0);
}
}
