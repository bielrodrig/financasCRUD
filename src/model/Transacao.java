package model;

import java.sql.Date;

public class Transacao {
    private int id;
    private String nome;
    private double valor;
    private String tipo;
    private Date data;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
}
