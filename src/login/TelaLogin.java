package view;

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

    private void validarLogin(ActionEvent e) {
        String usuario = txtUsuario.getText();
        String senha = new String(txtSenha.getPassword());

        // Validação simples (substitua com integração ao banco de dados mais tarde)
        if ("admin".equals(usuario) && "1234".equals(senha)) {
            JOptionPane.showMessageDialog(this, "Login bem-sucedido!");
            // Aqui você pode abrir a tela principal
            new TelaPrincipal();  // Chamando a tela principal
            dispose();  // Fechar a tela de login
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new TelaLogin();
    }
}
