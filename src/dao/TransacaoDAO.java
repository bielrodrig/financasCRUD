package dao;

import model.Transacao;
import util.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransacaoDAO {
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
            System.out.println("Erro ao inserir transacao"+e.getMessage());
        }
    }

    public static void main(String[] args) {
        TransacaoDAO dao = new TransacaoDAO();
        dao.inserirTransacao("Mercado", 130.9, Date.valueOf("2025-05-10"), "Saida");    }
}

