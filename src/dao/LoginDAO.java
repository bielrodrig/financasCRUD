package dao;

import util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public boolean validarLogin(String usuario, String senha) {
        String sql = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Se tiver resultado, login válido

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
