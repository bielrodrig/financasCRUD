package user;

import dao.CadastroDAO;
// import face.ReconhecimentoFacial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
// import java.io.ByteArrayInputStream;
// import java.io.InputStream;

public class TelaCadastro extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JTextField txtEmail;
    private JButton btnCadastrar;
    private JButton btnVoltar;

    public TelaCadastro() {
        setTitle("Cadastro - Sistema Financeiro");
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

        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(fonteLabel);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(lblUsuario, c);

        txtUsuario = new JTextField(35);
        txtUsuario.setFont(fonteCampo);
        c.gridx = 1;
        panel.add(txtUsuario, c);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(fonteLabel);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(lblSenha, c);

        txtSenha = new JPasswordField(35);
        txtSenha.setFont(fonteCampo);
        c.gridx = 1;
        panel.add(txtSenha, c);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(fonteLabel);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(lblEmail, c);

        txtEmail = new JTextField(35);
        txtEmail.setFont(fonteCampo);
        c.gridx = 1;
        panel.add(txtEmail, c);

        // Botão de cadastrar
        c.gridx = 1;
        c.gridy = 3;
        btnCadastrar = new JButton("Cadastrar");
        estilizarBotao(btnCadastrar);
        btnCadastrar.addActionListener(this::realizarCadastro);
        panel.add(btnCadastrar, c);

        // Botão voltar
        c.gridx = 1;
        c.gridy = 4;
        btnVoltar = new JButton("Voltar para Login");
        btnVoltar.setBorderPainted(false);
        btnVoltar.setContentAreaFilled(false);
        btnVoltar.setFont(new Font("Arial", Font.PLAIN, 13));
        btnVoltar.setForeground(Color.BLUE);
        btnVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnVoltar.addActionListener(e -> {
            new TelaLogin();
            dispose();
        });
        panel.add(btnVoltar, c);

        add(panel, BorderLayout.CENTER);
    }

    private void realizarCadastro(ActionEvent e) {
        String nome = txtUsuario.getText().trim();
        String email = txtEmail.getText().trim();
        String senha = new String(txtSenha.getPassword()).trim();

        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // --- BLOCO COMENTADO DO RECONHECIMENTO FACIAL ---
        /*
        ReconhecimentoFacial face = new ReconhecimentoFacial();
        byte[] imagem = face.capturarComInterface(); // Captura do rosto
        if (imagem == null) {
            JOptionPane.showMessageDialog(this, "Captura facial não realizada!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        InputStream imagemStream = new ByteArrayInputStream(imagem);
        int tamanhoImagem = imagem.length;
        */

        // Cadastro sem foto
        CadastroDAO dao = new CadastroDAO();
        dao.inserirUsuario(nome, email, senha, null, 0); // Passa null e 0 para imagem

        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        new TelaLogin();
        dispose();
    }

    private void estilizarBotao(JButton btn) {
        btn.setBackground(new Color(0, 120, 215));  // Azul
        btn.setForeground(Color.WHITE);             // Texto branco
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaCadastro::new);
    }
}
