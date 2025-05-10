package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost/financas"; // Corrigido 'jdbc'
    private static final String USUARIO = "root"; // Substitua por seu usuário
    private static final String SENHA = "";       // Substitua pela sua senha, se tiver

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado: " + e.getMessage());
            return null;
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao Banco de Dados: " + e.getMessage());
            return null;
        }
    }
}
