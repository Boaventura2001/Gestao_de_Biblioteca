package DAO;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConexaoBibliotecaDAO {

    public Connection conectaBiblioteca() throws ClassNotFoundException {

        Connection conn = null;

        try {
            Class.forName("org.gjt.mm.mysql.Driver");
            String url = "jdbc:mysql://localhost:3306/biblioteca?user=root&password=";
            conn = DriverManager.getConnection(url);
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ConexaoBibliotecaDAO: " + erro);
        }
        return conn;
    }
}
