package view;

import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import conexao.Conexao;
import net.proteanit.sql.DbUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import java.awt.Label;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Panel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class TelaOS extends JInternalFrame {
	private JTextField txtOs;
	private JTextField txtData;
	private JTextField txtCliPesquisar;
	private JTextField txtCliId;
	private JTable table;
	private JTextField txtOsEquip;
	private JTextField txtOsDef;
	private JTextField txtOsSer;
	private JTextField txtOsTec;
	private JTextField txtOsValor;
	private Connection connection ;
	private ResultSet result;
	private PreparedStatement statement;
	private String tipo = "";
	private JRadioButton rbtOs;
	private JComboBox cboOsSit;
	private JRadioButton rbtOr;
	private JButton btnOSAdicionar;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public TelaOS() {
		addInternalFrameListener(new InternalFrameAdapter() {
			@Override
			public void internalFrameOpened(InternalFrameEvent e) {
				rbtOs.setSelected(true);
				tipo = "OS";
			}
		});
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 576, 797, 554);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panel.setBounds(10, 11, 307, 148);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nº OS");
		lblNewLabel.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Data");
		lblNewLabel_1.setBounds(168, 11, 46, 14);
		panel.add(lblNewLabel_1);
		
		txtOs = new JTextField();
		txtOs.setEditable(false);
		txtOs.setBounds(10, 36, 86, 20);
		panel.add(txtOs);
		txtOs.setColumns(10);
		
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setBounds(166, 36, 113, 20);
		panel.add(txtData);
		txtData.setColumns(10);
		
		 rbtOr = new JRadioButton("Orçamento");
		rbtOr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Orçamento";
			}
		});
		rbtOr.setBounds(10, 112, 109, 23);
		panel.add(rbtOr);
		
	  rbtOs = new JRadioButton("Ordem de Serviço");
		rbtOs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "OS";
			}
		});
		rbtOs.setBounds(168, 112, 138, 23);
		panel.add(rbtOs);
		ButtonGroup  group = new ButtonGroup();
		group.add(rbtOr);
		group.add(rbtOs);
		
		Label label = new Label("Situação:");
		label.setBounds(10, 196, 62, 22);
		getContentPane().add(label);
		
		 cboOsSit = new JComboBox();
		cboOsSit.setModel(new DefaultComboBoxModel(new String[] { "Na bancada", "Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação", "Aguardando Peças", "Abandonado pelo Cliente","Retornou"}));
		cboOsSit.setBounds(77, 196, 240, 22);
		getContentPane().add(cboOsSit);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(327, 11, 444, 266);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		txtCliPesquisar = new JTextField();
		txtCliPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtCliPesquisar.setBounds(10, 25, 202, 20);
		panel_2.add(txtCliPesquisar);
		txtCliPesquisar.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(TelaOS.class.getResource("/icones/pesquisar.png")));
		lblNewLabel_2.setBounds(222, 19, 37, 32);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("* ID");
		lblNewLabel_3.setBounds(276, 28, 46, 14);
		panel_2.add(lblNewLabel_3);
		
		txtCliId = new JTextField();
		txtCliId.setEditable(false);
		txtCliId.setBounds(310, 25, 72, 20);
		panel_2.add(txtCliId);
		txtCliId.setColumns(10);
		
		JScrollPane tblClientes = new JScrollPane();
		tblClientes.setBounds(10, 62, 424, 193);
		panel_2.add(tblClientes);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Telefone"
			}
		));
		tblClientes.setViewportView(table);
		
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
		    		txtCliId.setText(table.getModel().getValueAt(selectedRow, 0).toString());
		    			            
		        }
		    }
		});
		
		JLabel lblNewLabel_4 = new JLabel("* Equipamento");
		lblNewLabel_4.setBounds(10, 263, 116, 14);
		getContentPane().add(lblNewLabel_4);
		
		txtOsEquip = new JTextField();
		txtOsEquip.setBounds(10, 287, 434, 20);
		getContentPane().add(txtOsEquip);
		txtOsEquip.setColumns(10);
		
		JLabel lblNewLabel_4_1 = new JLabel("* Defeito");
		lblNewLabel_4_1.setBounds(10, 318, 116, 14);
		getContentPane().add(lblNewLabel_4_1);
		
		txtOsDef = new JTextField();
		txtOsDef.setColumns(10);
		txtOsDef.setBounds(10, 342, 434, 20);
		getContentPane().add(txtOsDef);
		
		JLabel lblNewLabel_4_2 = new JLabel("Serviço");
		lblNewLabel_4_2.setBounds(10, 373, 116, 14);
		getContentPane().add(lblNewLabel_4_2);
		
		txtOsSer = new JTextField();
		txtOsSer.setColumns(10);
		txtOsSer.setBounds(10, 397, 434, 20);
		getContentPane().add(txtOsSer);
		
		JLabel lblNewLabel_4_2_1 = new JLabel("Técnico");
		lblNewLabel_4_2_1.setBounds(454, 289, 62, 16);
		getContentPane().add(lblNewLabel_4_2_1);
		
		txtOsTec = new JTextField();
		txtOsTec.setColumns(10);
		txtOsTec.setBounds(526, 287, 220, 20);
		getContentPane().add(txtOsTec);
		
		txtOsValor = new JTextField();
		txtOsValor.setText("0");
		txtOsValor.setColumns(10);
		txtOsValor.setBounds(516, 342, 230, 20);
		getContentPane().add(txtOsValor);
		
		JLabel lblNewLabel_4_2_1_1 = new JLabel("Valor Total");
		lblNewLabel_4_2_1_1.setBounds(454, 344, 62, 16);
		getContentPane().add(lblNewLabel_4_2_1_1);
		
		 btnOSAdicionar = new JButton("");
		btnOSAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionar();
			}
		});
		btnOSAdicionar.setIcon(new ImageIcon(TelaOS.class.getResource("/icones/create.png")));
		btnOSAdicionar.setToolTipText("Adicionar");
		btnOSAdicionar.setBounds(20, 433, 80, 80);
		getContentPane().add(btnOSAdicionar);
		
		JButton btnOsBuscar = new JButton("");
		btnOsBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			 pesquisar();
			}
		});
		btnOsBuscar.setIcon(new ImageIcon(TelaOS.class.getResource("/icones/read.png")));
		btnOsBuscar.setToolTipText("Pesquisar");
		btnOsBuscar.setBounds(120, 433, 80, 80);
		getContentPane().add(btnOsBuscar);
		
		JButton btnOsEditar = new JButton("");
		btnOsEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		btnOsEditar.setIcon(new ImageIcon(TelaOS.class.getResource("/icones/update.png")));
		btnOsEditar.setToolTipText("Editar");
		btnOsEditar.setBounds(221, 433, 80, 80);
		getContentPane().add(btnOsEditar);
		
		JButton btnOsDeletar = new JButton("");
		btnOsDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnOsDeletar.setIcon(new ImageIcon(TelaOS.class.getResource("/icones/delete.png")));
		btnOsDeletar.setToolTipText("Deletar");
		btnOsDeletar.setBounds(327, 433, 80, 80);
		getContentPane().add(btnOsDeletar);
		
		JButton btnOsImprimir = new JButton("");
		btnOsImprimir.setIcon(new ImageIcon(TelaOS.class.getResource("/icones/print.png")));
		btnOsImprimir.setToolTipText("Imprimir");
		btnOsImprimir.setBounds(436, 433, 80, 80);
		getContentPane().add(btnOsImprimir);
		
		Panel panel_1 = new Panel();
	
		panel_1.setLayout(null);
		
	}
	private void pesquisarCliente() {
		String  sql  = "select idcli as ID, nomecli as Nome, fonecli as Telefone  from tbclientes  where nomecli like ?";
		try {
			connection = Conexao.conection();
			statement = connection.prepareStatement(sql);
			statement.setString(1, txtCliPesquisar.getText()+"%");
			result = statement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(result));
			
		} catch (Exception e) {
			// TODO: handle exception
		JOptionPane.showMessageDialog(null, e);
		}
	}
	private void adicionar() {
		String sql = "INSERT INTO tbos (tipo, situacao, equipamento, defeito, servico, tecnico, valor, idcli) VALUES (?,?,?, ?,?,? ,?,?)";
		try {
			connection = Conexao.conection();
		  statement	=connection.prepareStatement(sql);
		  statement.setString(1,tipo);
		  statement.setString(2,cboOsSit.getSelectedItem().toString());
		  statement.setString(3, txtOsEquip.getText());
		  statement.setString(4, txtOsDef.getText());
		  statement.setString(5, txtOsSer.getText());
		  statement.setString(6, txtOsTec.getText());
		  statement.setString(7, txtOsValor.getText().replace(",","."));
		  statement.setString(8, txtCliId.getText());
		  
		  if(txtCliId.getText().isEmpty() || txtOsEquip.getText().isEmpty() || txtOsDef.getText().isEmpty()) {
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
	private void limpar() {
		txtCliId.setText(null);
		txtCliPesquisar.setText(null);
		txtOsValor.setText(null);
		txtOsDef.setText(null);
		txtOsEquip.setText(null);
		txtOsTec.setText(null);
		btnOSAdicionar.setEnabled(true);
		txtCliPesquisar.setEnabled(true);
		txtOs.setText(null);
		txtData.setText(null);
		table.setVisible(true);
	}
	
	private void pesquisar() {
		String numero = JOptionPane.showInputDialog("Número da OS");
		String sql ="select * from tbos  where os =?";
		
		try {
			connection = Conexao.conection();
			statement =connection.prepareStatement(sql);
			statement.setString(1, numero);
		   result =	statement.executeQuery();
		  if(result.next()) {
				DateFormat formatador = DateFormat.getDateInstance(DateFormat.SHORT);

			  txtOs.setText(result.getString(1));
			  txtData.setText(formatador.format(result.getDate(2)) );
			  String  tipo = result.getString(3);
			  if(tipo.equals("OS")) {
				  rbtOs.setSelected(true);
				  this.tipo = "OS";
			  }else {
				  rbtOr.setSelected(true);
				  this.tipo = "Orçamento";
			  }
			  cboOsSit.setSelectedItem(result.getString(4));
			  txtOsEquip.setText(result.getString(5));
			  txtOsDef.setText(result.getString(6));
			  txtOsSer.setText(result.getString(7));
			  txtOsTec.setText(result.getString(8));
			  txtOsValor.setText(result.getString(9).replace(".", ","));
			  txtCliId.setText(result.getString(10));
			  txtCliPesquisar.setEditable(false);
			  btnOSAdicionar.setEnabled(false);
			  table.setVisible(false);
			  
		  }else {
			  JOptionPane.showMessageDialog(null,"OS não encontrada");
		  }
		}catch (Exception e) {
			// TODO: handle exception
			 JOptionPane.showMessageDialog(null,e);
			 System.out.println(e);
		}
	}
	private void update() {
		System.out.println("Clicou");
		String sql ="update tbos set  tipo = ?, situacao= ?, equipamento = ?,defeito =  ?, servico = ?, tecnico = ? , valor = ?    where os =?";
		
		try {
			connection = Conexao.conection();
		  statement	=connection.prepareStatement(sql);
		  statement.setString(1,tipo);
		  statement.setString(2,cboOsSit.getSelectedItem().toString());
		  statement.setString(3, txtOsEquip.getText());
		  statement.setString(4, txtOsDef.getText());
		  statement.setString(5, txtOsSer.getText());
		  statement.setString(6, txtOsTec.getText());
		  statement.setString(7, txtOsValor.getText().replace(",","."));
		  statement.setString(8, txtOs.getText());
		  
		  if(txtCliId.getText().isEmpty() || txtOsEquip.getText().isEmpty() || txtOsDef.getText().isEmpty()) {
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
			
		}

	}
	private void delete() {
		String sql = "delete from tbos where os = ?";
		int opcao = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o OS?","Deletar", JOptionPane.YES_NO_OPTION);
		if(opcao == JOptionPane.YES_OPTION) {
			try {
				connection	= Conexao.conection();
				statement	= connection.prepareStatement(sql);
				statement.setInt(1,  Integer.parseInt(txtOs.getText()));
				int exec = statement.executeUpdate();
				if ( exec > 0) {
					JOptionPane.showMessageDialog(null, "OS deletado!!");
					limpar();
					btnOSAdicionar.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "OS não deletado!!", "Erro!", JOptionPane.ERROR_MESSAGE);

				}
			}catch (Exception e) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(null, e);
			}
		}
	}
}