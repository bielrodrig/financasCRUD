package user;

import dao.CadastroDAO;
import dao.LoginDAO;
import view.TelaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;

    public TelaLogin() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Painel de login
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        // Label e campo de texto para o usuário
        panel.add(new JLabel("Usuário:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        // Label e campo de senha
        panel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        panel.add(txtSenha);

        // Botão de login
        btnLogin = new JButton("Entrar");
        btnLogin.addActionListener(this::validarLogin);
        panel.add(btnLogin);

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void validarLogin(ActionEvent evt) {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        LoginDAO dao = new LoginDAO(); // agora usando a classe certa
        boolean valido = dao.validarLogin(usuario, senha);

        if (valido) {
            JOptionPane.showMessageDialog(this, "Login efetuado com sucesso!");
            new TelaPrincipal();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciais Inválidas!");
        }


    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}
