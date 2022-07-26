package DAO;

import DTO.UsuarioDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    Connection conn;

    public ResultSet autenticacaoUsuario(UsuarioDTO objusuariodto) throws ClassNotFoundException {
        conn = new ConexaoDAO().conectaBd();

        try {
            String sql = "select * from usuario where nome_usuario = ? AND senha_usuario = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, objusuariodto.getNome_usuario());
            pstmt.setString(2, objusuariodto.getSenha_usuario());
            
            ResultSet rs = pstmt.executeQuery();
            
            return rs;
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "UsuarioDAO: " + erro);
            return null;
        }
    }
}
