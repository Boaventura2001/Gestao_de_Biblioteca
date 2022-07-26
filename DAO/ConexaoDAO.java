package DAO;


import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.DriverManager;


public class ConexaoDAO {
    
    public static Connection conectaBd() throws ClassNotFoundException{
        Connection conn = null;
        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            String url = "jdbc:mysql://localhost:3306/bancoteste_usuario?user=root&password=";
            conn = DriverManager.getConnection(url);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return conn;
    }
}
