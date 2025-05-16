package user;

import dao.CadastroDAO;
import view.TelaPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class TelaCadastro extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JTextField txtEmail;
    private JButton btnCadastrar;


    public TelaCadastro() {


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 14, 14));


        panel.add(new JLabel("Usuário:"));
        txtUsuario = new JTextField();
        panel.add(txtUsuario);

        panel.add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        panel.add(txtSenha);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.addActionListener(this::cadastrarUsuario);
        panel.add(btnCadastrar);


        add(panel);
        setSize(400, 200);
        setLocationRelativeTo(null); // Centraliza na tela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);


    }

    private void cadastrarUsuario(ActionEvent evt) {
        String nomeUsuario = txtUsuario.getText();
        String senhaTexto = txtSenha.getText();
        String emailUsuario = txtEmail.getText();

        if (nomeUsuario.isEmpty() || senhaTexto.isEmpty() || emailUsuario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CadastroDAO cadastro = new CadastroDAO();
        cadastro.inserirUsuario(nomeUsuario, senhaTexto, emailUsuario);

        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
    }


    public static void main(String[] args) {
        new TelaCadastro();
    }
}
