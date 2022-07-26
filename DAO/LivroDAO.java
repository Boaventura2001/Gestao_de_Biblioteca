package DAO;

import DTO.ActualizarLivroDTO;
import DTO.ClienteDTO;
import DTO.EmprestarDTO;
import DTO.ExcluirLivroDTO;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import DTO.LivroDTO;
import DTO.PesquisarEmprestimoDTO;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LivroDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<LivroDTO> lista = new ArrayList<>();
    ArrayList<PesquisarEmprestimoDTO> listaPesquisarEmprestimo = new ArrayList<>();

    public void cadastrarLivro(LivroDTO objlivrodto) throws ClassNotFoundException {
        String sql = "insert into livro (titulo, autor) values (?, ?)";

        conn = new ConexaoBibliotecaDAO().conectaBiblioteca();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objlivrodto.getTitulo());
            pstm.setString(2, objlivrodto.getAutor());
            pstm.execute();

            JOptionPane.showMessageDialog(null, "Livro Cadastrado");
            pstm.close();

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "LivroDAO: " + erro);
        }
    }

    public void emprestarLivro(EmprestarDTO objemprestardto) throws ClassNotFoundException {
        String sql = "insert into emprestar (idCliente, idLivro, dataEmprestimo, dataDevolucao) values (?,?,curdate(),?)";
        conn = new ConexaoBibliotecaDAO().conectaBiblioteca();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objemprestardto.getIdCliente());
            pstm.setString(2, objemprestardto.getIdLivro());
            pstm.setString(3, objemprestardto.getDataDevolucao());

            pstm.execute();
            JOptionPane.showMessageDialog(null, "Livro Emprestado");
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "LivroDAO emprestarLivro(...): " + erro);
        }
    }

    public ArrayList<LivroDTO> pesquisarLivro() throws ClassNotFoundException {
        String sql = "select * from livro";
        conn = new ConexaoBibliotecaDAO().conectaBiblioteca();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                LivroDTO objlivrodto = new LivroDTO();
                objlivrodto.setIdLivro(rs.getInt("idLivro"));
                objlivrodto.setTitulo(rs.getString("titulo"));
                objlivrodto.setAutor(rs.getString("autor"));

                lista.add(objlivrodto);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "LivroDAO pesquisarLivro(): " + erro);
        }
        return lista;
    }

    public ArrayList pesquisarEmprestimo() throws ClassNotFoundException {
        String sql = "select cliente.nomeCliente, livro.titulo, emprestar.dataEmprestimo, emprestar.dataDevolucao from cliente\n"
                + "join emprestar\n"
                + "on cliente.idCliente = emprestar.idCliente\n"
                + "join livro\n"
                + "on livro.idLivro = emprestar.idLivro;";
        conn = new ConexaoBibliotecaDAO().conectaBiblioteca();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                PesquisarEmprestimoDTO objpesquisaremprestimodto = new PesquisarEmprestimoDTO();
                objpesquisaremprestimodto.setNomeCliente(rs.getString("nomeCliente"));
                objpesquisaremprestimodto.setTitulo(rs.getString("titulo"));
                objpesquisaremprestimodto.setDataEmprestimo(rs.getString("dataEmprestimo"));
                objpesquisaremprestimodto.setDataDevolucao(rs.getString("dataDevolucao"));

                listaPesquisarEmprestimo.add(objpesquisaremprestimodto);
            }

        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "LivroDAO pesquisarEmprestimo(): " + erro);
        }
        return listaPesquisarEmprestimo;
    }

    public void excluirLivro(ExcluirLivroDTO objexcluirlivrodto) throws ClassNotFoundException {
        String sql = "delete from livro where idLivro = ?";
        conn = new ConexaoBibliotecaDAO().conectaBiblioteca();
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objexcluirlivrodto.getIdLivro());
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Excluido");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "LivroDAO excluirLivro(): " + erro);
        }
    }

    public void actualizarLivro(ActualizarLivroDTO objactualizarlivrodto) throws ClassNotFoundException {
        String sql = "update livro\n"
                + "set titulo = ?\n"
                + "where idLivro = ?;";
        conn = new ConexaoBibliotecaDAO().conectaBiblioteca();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objactualizarlivrodto.getTitulo());
            pstm.setString(2, objactualizarlivrodto.getIdLivro());
            pstm.execute();
            JOptionPane.showMessageDialog(null, "Livro actualizado");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "LivroDAO actualizarLivro(...): " + erro);
        }
    }

}
