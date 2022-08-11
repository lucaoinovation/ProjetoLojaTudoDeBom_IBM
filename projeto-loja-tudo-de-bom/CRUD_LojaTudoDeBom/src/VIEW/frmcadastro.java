package VIEW;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DTO.cadastroDTO;
import connection.ClienteDAO;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class frmcadastro extends JFrame {

	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtEndereco;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel name = new JLabel("Nome");
		name.setBounds(10, 37, 46, 14);
		contentPane.add(name);
		
		txtNome = new JTextField();
		txtNome.setBounds(10, 62, 271, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel endereço = new JLabel("Endereço");
		endereço.setBounds(10, 107, 46, 14);
		contentPane.add(endereço);
		
		txtEndereco = new JTextField();
		txtEndereco.setColumns(10);
		txtEndereco.setBounds(10, 144, 271, 20);
		contentPane.add(txtEndereco);
		
		JButton btnCadastrar = new JButton("CADASTRAR");
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
		btnCadastrar.setBounds(10, 205, 93, 23);
		contentPane.add(btnCadastrar);
	}
}
