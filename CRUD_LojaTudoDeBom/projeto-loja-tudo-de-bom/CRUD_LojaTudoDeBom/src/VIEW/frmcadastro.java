package VIEW;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DTO.cadastroDTO;
import connection.ClienteDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLayeredPane;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.border.TitledBorder;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class frmcadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTable tableCliente;
	private JTextPane txtpnDeGerenciamentoDe;
	private JButton btnPesquisar;
	private JLabel lblNewLabel;
	private JTextField txtID;
	private JButton btnSetar;
	private JButton btnAlterar;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnCampos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmcadastro frame = new frmcadastro();
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
	public frmcadastro() {
		setBackground(Color.WHITE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 714);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel name = new JLabel("Nome");
		name.setBounds(20, 109, 67, 14);
		contentPane.add(name);
		
		txtNome = new JTextField();
		txtNome.setBounds(97, 106, 271, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel endereço = new JLabel("Endereço");
		endereço.setBounds(20, 134, 93, 14);
		contentPane.add(endereço);
		
		txtEndereco = new JTextField();
		txtEndereco.setBounds(97, 131, 271, 20);
		txtEndereco.setColumns(10);
		contentPane.add(txtEndereco);
		
		JButton btnCadastrar = new JButton("CADASTRAR");
		btnCadastrar.setBounds(20, 187, 129, 23);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastrarCliente();
				SetarCampos();
				BuscarCliente();
			}
		});
		contentPane.add(btnCadastrar);
		
		JTextPane txtpnLojaTudoDe = new JTextPane();
		txtpnLojaTudoDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtpnLojaTudoDe.setText("Loja Tudo De Bom");
		txtpnLojaTudoDe.setBounds(10, 11, 271, 33);
		contentPane.add(txtpnLojaTudoDe);
		
		txtpnDeGerenciamentoDe = new JTextPane();
		txtpnDeGerenciamentoDe.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnDeGerenciamentoDe.setText("Sistema de Gerenciamento de Cadastros");
		txtpnDeGerenciamentoDe.setBounds(10, 44, 271, 20);
		contentPane.add(txtpnDeGerenciamentoDe);
		
		btnPesquisar = new JButton("LISTAR CLIENTES");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BuscarCliente();
				SetarCampos();
			}
		});
		btnPesquisar.setBounds(252, 187, 172, 23);
		contentPane.add(btnPesquisar);
		
		JTextPane txtpnCadastroDeClientes = new JTextPane();
		txtpnCadastroDeClientes.setText("Cadastro de Clientes");
		txtpnCadastroDeClientes.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnCadastroDeClientes.setBounds(10, 75, 271, 20);
		contentPane.add(txtpnCadastroDeClientes);
		
		lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(378, 109, 46, 14);
		contentPane.add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setEnabled(false);
		txtID.setBounds(398, 106, 46, 20);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		btnSetar = new JButton("LIMPAR");
		btnSetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetarCampos();
			}
		});
		btnSetar.setBounds(158, 187, 89, 23);
		contentPane.add(btnSetar);
		
		btnAlterar = new JButton("ALTERAR");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlterarCliente();
				BuscarCliente();
				SetarCampos();
			}
		});
		btnAlterar.setBounds(20, 216, 89, 23);
		contentPane.add(btnAlterar);
		
		btnCampos = new JButton("SELECIONAR");
		btnCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Campos();
			}
		});
		btnCampos.setBounds(784, 587, 115, 23);
		contentPane.add(btnCampos);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 257, 879, 321);
		contentPane.add(scrollPane);
		scrollPane.setEnabled(false);
		
		tableCliente = new JTable();
		tableCliente.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(tableCliente);
		tableCliente.setAutoCreateRowSorter(true);
		tableCliente.setSurrendersFocusOnKeystroke(true);
		tableCliente.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableCliente.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
			}
		});
		tableCliente.setToolTipText("");
		tableCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "NOME", "ENDERE\u00C7O"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		panel.setLayout(null);
		
		JButton btnExcluir = new JButton("EXCLUIR");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ExcluirCliente();
				SetarCampos();
				BuscarCliente();
			}
		});
		btnExcluir.setBounds(158, 216, 89, 23);
		contentPane.add(btnExcluir);
		tableCliente.getColumnModel().getColumn(0).setPreferredWidth(57);
		tableCliente.getColumnModel().getColumn(1).setPreferredWidth(227);
	}
	
	private void CadastrarCliente() {
		
		String nome, endereco;
		
		nome = txtNome.getText();
		endereco =txtEndereco.getText();
		
		cadastroDTO objcadastrodto = new cadastroDTO();
		objcadastrodto.setNome_cliente(nome);
		objcadastrodto.setEndereco_cliente(endereco);
		
		ClienteDAO objclientedao = new ClienteDAO();
		objclientedao.cadastrarCliente(objcadastrodto);
	}
	
	private void BuscarCliente() {
		try {
			ClienteDAO objclientedao = new ClienteDAO();
			
			DefaultTableModel model = (DefaultTableModel) tableCliente.getModel();
			model.setNumRows(0);
			
			ArrayList<cadastroDTO> lista = objclientedao.ListarCliente();
			
			for(int num = 0; num < lista.size(); num++) {
				model.addRow(new Object[] {
					lista.get(num).getId_cliente(),
					lista.get(num).getNome_cliente(),
					lista.get(num).getEndereco_cliente()
				});
			}
			
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Listar Cliente VIEW: " + erro);
		}
	}
	
	private void Campos() {
		int set = tableCliente.getSelectedRow();
		
		txtID.setText(tableCliente.getModel().getValueAt(set, 0).toString());
		txtNome.setText(tableCliente.getModel().getValueAt(set, 1).toString());
		txtEndereco.setText(tableCliente.getModel().getValueAt(set, 2).toString());
	}
	
	private void SetarCampos() {
		txtID.setText("");
		txtNome.setText("");
		txtEndereco.setText("");
		txtNome.requestFocus();
	}
	
	private void AlterarCliente() {
		int id_cliente;
		String nome_cliente,endereco_cliente;
		
		id_cliente = Integer.parseInt(txtID.getText());
		nome_cliente = txtNome.getText();
		endereco_cliente = txtEndereco.getText();
		
		cadastroDTO objcadastrodto = new cadastroDTO();
		objcadastrodto.setId_cliente(id_cliente);
		objcadastrodto.setNome_cliente(nome_cliente);
		objcadastrodto.setEndereco_cliente(endereco_cliente);
		
		ClienteDAO objclientedao = new ClienteDAO();
		objclientedao.alterarCliente(objcadastrodto);
	}
	
	private void ExcluirCliente() {
		int id_cliente;
		
		id_cliente = Integer.parseInt(txtID.getText());
		
		cadastroDTO objcadastroDTO = new cadastroDTO();
		objcadastroDTO.setId_cliente(id_cliente);
		
		ClienteDAO objclientedao = new ClienteDAO();
		objclientedao.excluirCliente(objcadastroDTO);
	}
}
