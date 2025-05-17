package dao;

import util.Conexao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CadastroDAO {

    public void inserirUsuario(String nome, String email, String senha, InputStream imagemFace, int tamanhoImagem) {
        String sql = "INSERT INTO usuarios (nome, email, senha, foto_face) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, email);
            stmt.setString(3, senha);
            stmt.setBinaryStream(4, imagemFace, tamanhoImagem);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
