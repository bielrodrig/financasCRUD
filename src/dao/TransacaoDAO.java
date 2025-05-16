package dao;

import model.Transacao;
import util.Conexao;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class TransacaoDAO {

    public List<Transacao> listarTransacoes() {
        List<Transacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM transacoes";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Transacao t = new Transacao();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setValor(rs.getDouble("valor"));
                t.setTipo(rs.getString("tipo"));
                t.setData(rs.getDate("data"));
                lista.add(t);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar transações: " + e.getMessage());
        }

        return lista;
    }

    public void inserirTransacao(String nome, double valor, java.sql.Date data, String tipo) {
        String sql = "INSERT INTO transacoes (nome, valor, data, tipo) VALUES (?, ?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setDouble(2, valor);
            stmt.setDate(3, data);
            stmt.setString(4, tipo);
            stmt.executeUpdate();


            System.out.println(" Transação inserida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir transacao" + e.getMessage());
        }
    }
    public void removerTransacao(int id) {
        String sql = "DELETE FROM transacoes WHERE id = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println(" Transação removida com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao remover transacao" + e.getMessage());
        }
    }
}



