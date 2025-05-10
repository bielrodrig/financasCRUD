package model;

import java.time.LocalDate;

public class Transacao {
    private String data;
    private double valor;
    private String tipo; // Saida ou Entrada de valor
    private LocalDate dataLocal;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataLocal() {
        return dataLocal;
    }

    public void setDataLocal(LocalDate dataLocal) {
        this.dataLocal = dataLocal;
    }

    public Transacao(int id, double valor, String tipo, LocalDate dataLocal) {
        this.id = id;
        this.valor = valor;
        this.tipo = tipo;
        this.dataLocal = dataLocal;
    }
    public Transacao(double valor, String tipo, LocalDate dataLocal) {
        this.valor = valor;
        this.tipo = tipo;
        this.dataLocal = dataLocal;
    }


}
