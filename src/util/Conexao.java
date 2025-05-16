package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String URL = "jdbc:mysql://localhost/financas";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

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
