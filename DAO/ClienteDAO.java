package DAO;

import DTO.ClienteDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ClienteDAO {
    
    Connection conn;
    PreparedStatement pstm;
    public void cadastrarCliente(ClienteDTO objclientedao) throws ClassNotFoundException, SQLException{
        String sql = "insert into cliente (nomeCliente, contacto, email) values(?,?,?)";
        
        conn = new ConexaoBibliotecaDAO().conectaBiblioteca();
        
        try {
           pstm = conn.prepareStatement(sql);
           pstm.setString(1, objclientedao.getNomeCliente());
           pstm.setString(2, objclientedao.getTelefone());
           pstm.setString(3, objclientedao.getEmail());
           pstm.execute();
           
           JOptionPane.showMessageDialog(null, "Cliente Cadastrado");
           pstm.close();
           
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ClienteDAO: " + erro);
        }
        
    }
}
