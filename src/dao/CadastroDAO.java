package dao;

import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroDAO {
    public void inserirUsuario(String email, String nome, String senha) {
        String sql = "INSERT INTO usuarios (nome, senha, email) VALUES (?, ?, ?)";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            stmt.setString(2, nome);
            stmt.setString(3, senha);

            stmt.executeUpdate();

            System.out.println("Cadastro efetuado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir usuario!" + e.getMessage());
        }

    }
}
