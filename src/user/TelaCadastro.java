package user;

import dao.CadastroDAO;
import face.ReconhecimentoFacial;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class TelaCadastro extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JTextField txtEmail;
    private JButton btnCadastrar;
    private JButton btnFace;

    // Armazena imagem facial capturada
    private byte[] imagemFacial;

    public TelaCadastro() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 6, 18, 18));

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
        btnCadastrar.addActionListener(e -> {
            String nome = txtUsuario.getText();
            String email = txtEmail.getText();
            String senha = txtSenha.getText();

            ReconhecimentoFacial face = new ReconhecimentoFacial();
            byte[] imagem = face.capturarComInterface(); // captura o rosto
            InputStream imagemStream = new ByteArrayInputStream(imagem);
            int tamanhoImagem = imagem.length;

            CadastroDAO dao = new CadastroDAO();
            dao.inserirUsuario(nome, email, senha, imagemStream, tamanhoImagem);

            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
        });
        panel.add(btnCadastrar);


        add(panel);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }



    public static void main(String[] args) {
        new TelaCadastro();
    }
}
