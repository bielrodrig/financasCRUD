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
        setTitle("Cadastro de Transação");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(10, 4));

        formPanel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        formPanel.add(txtNome);

        formPanel.add(new JLabel("Valor:"));
        txtValor = new JTextField();
        formPanel.add(txtValor);

        formPanel.add(new JLabel("Tipo:"));
        cmbTipo = new JComboBox<>(new String[]{"Entrada", "Saída"});
        formPanel.add(cmbTipo);

        formPanel.add(new JLabel("Data (YYYY-MM-dd):"));
        txtData = new JTextField();
        formPanel.add(txtData);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.addActionListener(this::salvarTransacao);
        formPanel.add(btnSalvar);

        JButton btnListar = new JButton("Listar Transações");
        btnListar.addActionListener(this::carregarTransacoes);
        formPanel.add(btnListar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(this::excluirTransacoes);
        formPanel.add(btnExcluir);

        add(formPanel, BorderLayout.NORTH);

        // Tabela
        tabelaModel = new DefaultTableModel(new String[]{"ID", "Nome", "Valor", "Tipo", "Data"}, 0);
        tabela = new JTable(tabelaModel);
        JScrollPane scrollPane = new JScrollPane(tabela);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
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
            int confirm = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir a transação", "Confirmar exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int id = (int) tabelaModel.getValueAt(linhaSelecionada, 0);
                TransacaoDAO dao = new TransacaoDAO();
                dao.removerTransacao(id);
                carregarTransacoes(null);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione uma transação para remover.");

            }
        }
    }

    private void carregarTransacoes(ActionEvent evt) {
        tabelaModel.setRowCount(0); // limpa a tabela
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

    public static void main(String[] args) {
        new TelaPrincipal();
    }
}
