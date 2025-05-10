package view;

import dao.TransacaoDAO;
import model.Transacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;

public class TelaPrincipal extends JFrame {
    private JTextField txtNome, txtValor, txtData;
    private JComboBox<String> cmbTipo;

    public TelaPrincipal() {
        setTitle("Cadastro de Transação");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new GridLayout(5, 2));

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("Valor:"));
        txtValor = new JTextField();
        add(txtValor);

        add(new JLabel("Tipo:"));
        cmbTipo = new JComboBox<>();
        add(cmbTipo);

        add(new JLabel("Data (YYYY-MM-dd):)"));
        txtData = new JTextField();
        add(txtData);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this::salvarTransacao);
        add(btnSalvar);

        setVisible(true);
    }

    private void salvarTransacao(ActionEvent evt) {
        String nome = txtNome.getText();
        double valor = Double.parseDouble(txtValor.getText()); // ✅
        Date data = Date.valueOf(txtData.getText());
        String tipo = cmbTipo.getSelectedItem().toString();
        TransacaoDAO dao = new TransacaoDAO();
        dao.inserirTransacao(nome, valor, data, tipo);

        JOptionPane.showMessageDialog(this, "Transação salva com sucesso!");
        limparCampos();
    }

    private void limparCampos() {
        txtNome.setText("");
        txtValor.setText("");
        txtData.setText("");
        cmbTipo.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }

}
