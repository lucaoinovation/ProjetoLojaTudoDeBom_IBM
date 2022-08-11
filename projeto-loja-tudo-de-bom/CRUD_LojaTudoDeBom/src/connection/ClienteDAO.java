package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import DTO.cadastroDTO;

public class ClienteDAO {
	
	Connection conn;
	PreparedStatement pstm;
	
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
			JOptionPane.showMessageDialog(null, "ClienteDAO" + erro);
		}
		
	}

}
