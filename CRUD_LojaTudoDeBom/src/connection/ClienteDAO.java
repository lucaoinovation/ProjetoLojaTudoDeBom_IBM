package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import DTO.cadastroDTO;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
	
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<cadastroDTO> lista = new ArrayList<>();
	
	public void cadastrarCliente(cadastroDTO objcadastrodto) {
		String sql = "insert into cliente (nome_cliente, endereco_cliente) values (?,?)";
		
		conn = new ConexaoDAO().conectaBD();
		
		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, objcadastrodto.getNome_cliente());
			pstm.setString(2, objcadastrodto.getEndereco_cliente());
			
			pstm.execute();
			pstm.close();
			
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "ClienteDAO Cadastrar" + erro);
		}
		
	}
	
	public ArrayList<cadastroDTO> ListarCliente() {
		String sql = "select*from cliente";
		
		conn = new ConexaoDAO().conectaBD();
		
		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				cadastroDTO objcadastroDTO = new cadastroDTO();
				objcadastroDTO.setId_cliente(rs.getInt("id_cliente"));
				objcadastroDTO.setNome_cliente(rs.getString("nome_cliente"));
				objcadastroDTO.setEndereco_cliente(rs.getString("endereco_cliente"));
				
				lista.add(objcadastroDTO);
			}
			
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "ClienteDAO Listar:" + erro);
		}
		return lista;
	}


}
