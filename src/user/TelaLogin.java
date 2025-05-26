package user;

import dao.LoginDAO;
import view.TelaPrincipal;
import user.TelaCadastro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnLogin;
    private JCheckBox chkMostrarSenha;
    private JButton btnCadastro;

    public TelaLogin() {
        setTitle("Login - Sistema Financeiro");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        adicionarComponentes();

        setVisible(true);
    }

    private void adicionarComponentes() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.setBackground(Color.WHITE);

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 10, 10, 10);
        c.fill = GridBagConstraints.HORIZONTAL;

        Font fonteLabel = new Font("Arial", Font.BOLD, 16);
        Font fonteCampo = new Font("Arial", Font.PLAIN, 14);

        // Label Usuário
        c.gridx = 0;
        c.gridy = 0;
        panel.add(new JLabel("Usuário:"), c);

        // Campo Usuário
        c.gridx = 1;
        txtUsuario = new JTextField(35);
        txtUsuario.setFont(fonteCampo);
        panel.add(txtUsuario, c);

        // Label Senha
        c.gridx = 0;
        c.gridy = 1;
        panel.add(new JLabel("Senha:"), c);

        // Campo Senha
        c.gridx = 1;
        txtSenha = new JPasswordField(35);
        txtSenha.setFont(fonteCampo);
        panel.add(txtSenha, c);

        // Checkbox Mostrar Senha
        c.gridx = 1;
        c.gridy = 2;
        chkMostrarSenha = new JCheckBox("Mostrar senha");
        chkMostrarSenha.setBackground(Color.WHITE);
        chkMostrarSenha.setFont(new Font("Arial", Font.PLAIN, 13));
        chkMostrarSenha.addActionListener(e -> {
            if (chkMostrarSenha.isSelected()) {
                txtSenha.setEchoChar((char) 0);
            } else {
                txtSenha.setEchoChar('•');
            }
        });
        panel.add(chkMostrarSenha, c);

        // Botão Login
        c.gridx = 1;
        c.gridy = 3;
        btnLogin = new JButton("Entrar");
        estilizarBotao(btnLogin);
        btnLogin.addActionListener(this::validarLogin);
        panel.add(btnLogin, c);

        // Botão Cadastro
        c.gridx = 1;
        c.gridy = 4;
        btnCadastro = new JButton("Não tem cadastro? Clique aqui");
        btnCadastro.setBorderPainted(false);
        btnCadastro.setContentAreaFilled(false);
        btnCadastro.setFont(new Font("Arial", Font.PLAIN, 13));
        btnCadastro.setForeground(Color.BLUE);
        btnCadastro.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastro.addActionListener(this::abrirTelaCadastro);
        panel.add(btnCadastro, c);

        add(panel, BorderLayout.CENTER);
    }

    private void validarLogin(ActionEvent evt) {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (usuario.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LoginDAO dao = new LoginDAO();
        boolean valido = dao.validarLogin(usuario, senha);

        if (valido) {
            JOptionPane.showMessageDialog(this, "Login efetuado com sucesso!", "Bem-vindo", JOptionPane.INFORMATION_MESSAGE);
            new TelaPrincipal();
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuário ou senha inválidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirTelaCadastro(ActionEvent evt) {
        new TelaCadastro();
        dispose();
    }
    private void estilizarBotao(JButton btn) {
        btn.setBackground(new Color(0, 120, 215));  // Azul
        btn.setForeground(Color.BLACK);             // Texto preto
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Espaçamento interno
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaLogin::new);
    }

}
