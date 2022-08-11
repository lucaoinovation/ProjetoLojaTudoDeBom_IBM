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

public class frmcadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEndereco;
	private JTable tableCliente;
	private JTextPane txtpnDeGerenciamentoDe;
	private JButton btnPesquisar;

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
		btnCadastrar.setBounds(20, 216, 129, 23);
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nome, endereco;
				
				nome = txtNome.getText();
				endereco =txtEndereco.getText();
				
				cadastroDTO objcadastrodto = new cadastroDTO();
				objcadastrodto.setNome_cliente(nome);
				objcadastrodto.setEndereco_cliente(endereco);
				
				ClienteDAO objclientedao = new ClienteDAO();
				objclientedao.cadastrarCliente(objcadastrodto);
			}
		});
		contentPane.add(btnCadastrar);
		
		tableCliente = new JTable();
		tableCliente.setCellSelectionEnabled(true);
		tableCliente.setColumnSelectionAllowed(true);
		tableCliente.setFillsViewportHeight(true);
		tableCliente.setToolTipText("ID");
		tableCliente.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, "", null},
			},
			new String[] {
				"id_cliente", "nome_cliente", "Endere\u00E7o", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableCliente.getColumnModel().getColumn(0).setPreferredWidth(57);
		tableCliente.getColumnModel().getColumn(1).setPreferredWidth(326);
		tableCliente.getColumnModel().getColumn(2).setPreferredWidth(367);
		tableCliente.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableCliente.setBounds(20, 272, 879, 279);
		contentPane.add(tableCliente);
		
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
			}
		});
		btnPesquisar.setBounds(159, 216, 172, 23);
		contentPane.add(btnPesquisar);
		
		JTextPane txtpnCadastroDeClientes = new JTextPane();
		txtpnCadastroDeClientes.setText("Cadastro de Clientes");
		txtpnCadastroDeClientes.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtpnCadastroDeClientes.setBounds(10, 75, 271, 20);
		contentPane.add(txtpnCadastroDeClientes);
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
}
