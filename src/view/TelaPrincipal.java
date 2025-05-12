package view;

import dao.TransacaoDAO;
import model.Transacao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.List;

public class TelaPrincipal extends JFrame {
    private JTextField txtNome, txtValor, txtData;
    private JComboBox<String> cmbTipo;
    private JTable tabela;
    private DefaultTableModel tabelaModel;

    public TelaPrincipal() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("Gerenciador de Finanças");
        setSize(900, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        painelPrincipal.setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Gerenciador de Finanças");
        titulo.setFont(new Font("Arial", Font.BOLD, 26));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        painelPrincipal.add(titulo, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font inputFont = new Font("Arial", Font.PLAIN, 14);

        txtNome = new JTextField();
        txtValor = new JTextField();
        txtData = new JTextField();
        cmbTipo = new JComboBox<>(new String[]{"Entrada", "Saída"});

        txtNome.setFont(inputFont);
        txtValor.setFont(inputFont);
        txtData.setFont(inputFont);
        cmbTipo.setFont(inputFont);

        adicionarCampo(formPanel, gbc, 0, "Nome:", txtNome);
        adicionarCampo(formPanel, gbc, 1, "Valor:", txtValor);
        adicionarCampo(formPanel, gbc, 2, "Tipo:", cmbTipo);
        adicionarCampo(formPanel, gbc, 3, "Data (YYYY-MM-DD):", txtData);

        JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        botoes.setBackground(Color.WHITE);

        JButton btnSalvar = new JButton("Salvar");
        estilizarBotao(btnSalvar);
        btnSalvar.addActionListener(this::salvarTransacao);

        JButton btnListar = new JButton("Listar Transações");
        estilizarBotao(btnListar);
        btnListar.addActionListener(this::carregarTransacoes);

        JButton btnExcluir = new JButton("Excluir");
        estilizarBotao(btnExcluir);
        btnExcluir.addActionListener(this::excluirTransacoes);

        botoes.add(btnSalvar);
        botoes.add(btnListar);
        botoes.add(btnExcluir);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        formPanel.add(botoes, gbc);

        painelPrincipal.add(formPanel, BorderLayout.CENTER);

        tabelaModel = new DefaultTableModel(new String[]{"ID", "Nome", "Valor", "Tipo", "Data"}, 0);
        tabela = new JTable(tabelaModel);
        tabela.setFont(inputFont);
        tabela.setRowHeight(24);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Transações"));

        painelPrincipal.add(scrollPane, BorderLayout.SOUTH);

        add(painelPrincipal);
        setVisible(true);
    }

    private void adicionarCampo(JPanel panel, GridBagConstraints gbc, int y, String label, JComponent campo) {
        gbc.gridx = 0;
        gbc.gridy = y;
        gbc.weightx = 0;
        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        campo.setPreferredSize(new Dimension(200, 30));
        panel.add(campo, gbc);
    }

    private void salvarTransacao(ActionEvent evt) {
        String nome = txtNome.getText();
        double valor = Double.parseDouble(txtValor.getText());
        String tipo = cmbTipo.getSelectedItem().toString();
        Date data = Date.valueOf(txtData.getText());

        TransacaoDAO dao = new TransacaoDAO();
        dao.inserirTransacao(nome, valor, data, tipo);

        JOptionPane.showMessageDialog(this, "Transação salva com sucesso!");
        limparCampos();
    }

    private void excluirTransacoes(ActionEvent evt) {
        int linhaSelecionada = tabela.getSelectedRow();
        if (linhaSelecionada >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir a transação?", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) tabelaModel.getValueAt(linhaSelecionada, 0);
                TransacaoDAO dao = new TransacaoDAO();
                dao.removerTransacao(id);
                carregarTransacoes(null);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma transação para remover.");
        }
    }

    private void carregarTransacoes(ActionEvent evt) {
        tabelaModel.setRowCount(0);
        TransacaoDAO dao = new TransacaoDAO();
        List<Transacao> lista = dao.listarTransacoes();

        for (Transacao t : lista) {
            tabelaModel.addRow(new Object[]{
                    t.getId(),
                    t.getNome(),
                    t.getValor(),
                    t.getTipo(),
                    t.getData()
            });
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtValor.setText("");
        txtData.setText("");
        cmbTipo.setSelectedIndex(0);
    }

    private void estilizarBotao(JButton btn) {
        btn.setBackground(new Color(0, 120, 215));
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Arial", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }
}
